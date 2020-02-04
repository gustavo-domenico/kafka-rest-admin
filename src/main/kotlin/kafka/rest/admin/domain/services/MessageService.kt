package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.factories.AdminClientFactory
import kafka.rest.admin.domain.models.Message
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.TopicPartition
import org.springframework.stereotype.Service
import java.time.Duration.ofMillis
import java.util.*

@Service
class MessageService(adminClientFactory: AdminClientFactory) : KafkaService(adminClientFactory) {
    private fun find(consumer: KafkaConsumer<String, String>, topicPartition: TopicPartition, beginOffset: Long, endOffset: Long): List<Message> {
        val rawRecords = ArrayList<ConsumerRecord<String, String>>()

        consumer.assign(listOf(topicPartition))
        consumer.seek(topicPartition, beginOffset)

        var currentOffset = beginOffset - 1
        while (currentOffset < endOffset) {
            val polled = consumer.poll(ofMillis(200)).records(topicPartition)
            if (polled.isNotEmpty()) {
                rawRecords.addAll(polled)
                currentOffset = polled[polled.size - 1].offset()
            }
        }
        return rawRecords.map { m -> Message(m.value()) }
    }

    fun offset(topic: String, partition: Int, offset: Long): Message {
        return find(consumer(), TopicPartition(topic, partition), offset, offset).first()
    }

    fun from(topic: String, partition: Int, offset: Long): List<Message> {
        val newConsumer = consumer()
        val topicPartition = TopicPartition(topic, partition)
        val latestOffset = newConsumer
                .endOffsets(listOf(topicPartition))[topicPartition]!! - 1

        return find(newConsumer, topicPartition, offset, latestOffset)
    }
}
