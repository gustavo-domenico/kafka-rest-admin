package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.factories.AdminClientFactory
import kafka.rest.admin.domain.models.Message
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.TopicPartition
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.*

@Service
class MessageService(adminClientFactory: AdminClientFactory) : KafkaService(adminClientFactory) {
    fun from(topic: String, partition: Int, offset: Long): List<Message> {
        val myConsumer = consumer()
        val tp = TopicPartition(topic, partition)
        val rawRecords = ArrayList<ConsumerRecord<String, String>>()

        myConsumer.assign(listOf(tp))
        myConsumer.seek(tp, offset)

        val latestOffset = myConsumer.endOffsets(listOf(tp)).get(tp)!! - 1

        var currentOffset = offset - 1
        while (currentOffset < latestOffset) {
            val polled = myConsumer.poll(Duration.ofMillis(200)).records(tp)
            if (polled.isNotEmpty()) {
                rawRecords.addAll(polled)
                currentOffset = polled[polled.size - 1].offset()
            }
        }
        println(rawRecords)
        return rawRecords.map { m -> Message(m.value()) }
    }
}
