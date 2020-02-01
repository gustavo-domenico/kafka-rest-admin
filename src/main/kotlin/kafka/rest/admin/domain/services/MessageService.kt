package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.factories.AdminClientFactory
import kafka.rest.admin.domain.models.Message
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.TopicPartition
import org.springframework.stereotype.Service
import java.time.Duration.ofMillis
import java.util.*

@Service
class MessageService(adminClientFactory: AdminClientFactory) : KafkaService(adminClientFactory) {
    fun from(topic: String, partition: Int, offset: Long): List<Message> {
        val rawRecords = ArrayList<ConsumerRecord<String, String>>()
        val tp = TopicPartition(topic, partition)

        val consumer = consumer();
        consumer().assign(listOf(tp))
        consumer.seek(tp, offset)

        val latestOffset = consumer.endOffsets(listOf(tp)).get(tp)!! - 1

        var currentOffset = offset - 1
        while (currentOffset < latestOffset) {
            val polled = consumer.poll(ofMillis(200)).records(tp)
            if (polled.isNotEmpty()) {
                rawRecords.addAll(polled)
                currentOffset = polled[polled.size - 1].offset()
            }
        }
        return rawRecords.map { m -> Message(m.value()) }
    }
}
