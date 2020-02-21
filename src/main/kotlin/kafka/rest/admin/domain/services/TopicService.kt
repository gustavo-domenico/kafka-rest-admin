package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.models.KafkaNode
import kafka.rest.admin.domain.models.Topic
import kafka.rest.admin.domain.models.TopicDetail
import kafka.rest.admin.domain.models.TopicPartition
import kafka.rest.admin.infrastructure.helpers.runForEntity
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.admin.TopicDescription
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.springframework.stereotype.Service
import org.apache.kafka.common.TopicPartition as KafkaTopicPartition

@Service
class TopicService(val client: AdminClient, val consumer: KafkaConsumer<String, String>) {
    private fun getLatestOffset(c: KafkaConsumer<String, String>, topic: String, partition: Int): Long {
        val topicPartition = KafkaTopicPartition(topic, partition)
        return c.endOffsets(listOf(topicPartition))[topicPartition]!!
    }

    fun list(): List<Topic> =
            client.use {
                it.listTopics().listings()
                        .get().map { l -> Topic(l.name()) }
            }

    fun get(name: String): TopicDetail =
            runForEntity(name) {
                client.use {
                    getTopicDetails(it, name)
                }
            }

    fun add(name: String, partitions: Int): TopicDetail =
            client.use {
                it.createTopics(listOf(NewTopic(name, partitions, 1.toShort())))
                        .all()
                getTopicDetails(it, name)
            }

    private fun getTopicDetails(client: AdminClient, name: String): TopicDetail =
            client.describeTopics(mutableListOf(name))
                    .values()[name]!!
                    .get()
                    .let {
                        topicDetail(it, name)
                    }

    private fun topicDetail(it: TopicDescription, name: String): TopicDetail {
        consumer.use { c ->
            return TopicDetail(
                    Topic(it.name()),
                    it.partitions().size,
                    it.partitions().mapIndexed { i, p ->
                        TopicPartition(i, p.leader().let { l -> KafkaNode(l.id(), l.host(), l.port()) },
                                getLatestOffset(c, name, p.partition()))
                    })
        }
    }
}
