package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.factories.AdminClientFactory
import kafka.rest.admin.domain.models.KafkaNode
import kafka.rest.admin.domain.models.Topic
import kafka.rest.admin.domain.models.TopicDetail
import kafka.rest.admin.domain.models.TopicPartition
import kafka.rest.admin.infrastructure.helpers.runForEntity
import org.apache.kafka.clients.admin.TopicDescription
import org.springframework.stereotype.Service

import org.apache.kafka.common.TopicPartition as KafkaTopicPartition

@Service
class TopicService(adminClientFactory: AdminClientFactory) : KafkaService(adminClientFactory) {
    private fun getLatestOffset(topic: String, partition: Int): Long {
        val topicPartition = KafkaTopicPartition(topic, partition)
        return consumer().use {
            it.endOffsets(listOf(topicPartition))[topicPartition]!!
        }
    }

    fun list(): List<Topic> =
            client().use {
                it.listTopics().listings()
                        .get().map { l -> Topic(l.name()) }
            }

    fun get(name: String): TopicDetail =
            runForEntity(name) {
                client().use { client ->
                    client.describeTopics(mutableListOf(name))
                            .values()[name]!!
                            .get()
                            .let {
                                topicDetail(it, name)
                            }
                }
            }

    private fun topicDetail(it: TopicDescription, name: String): TopicDetail {
        return TopicDetail(
                Topic(it.name()),
                it.partitions().size,
                it.partitions().mapIndexed { i, p ->
                    TopicPartition(i, p.leader().let { l -> KafkaNode(l.id(), l.host(), l.port()) },
                            getLatestOffset(name, p.partition()))
                }

        )
    }
}
