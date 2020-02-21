package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.models.Message
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.TopicPartition
import org.springframework.stereotype.Service
import java.time.Duration.ofMillis
import java.util.*

@Service
class MessageService(val consumer: KafkaConsumer<String, String>, val producer: KafkaProducer<String, String>) {
    private fun find(consumer: KafkaConsumer<String, String>, topicPartition: TopicPartition, beginOffset: Long, endOffset: Long): List<Message> {
        val rawRecords = ArrayList<ConsumerRecord<String?, String>>()

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
        return rawRecords.map { m -> Message(m.key(), m.value(), m.offset(), m.timestamp()) }
    }

    fun offset(topic: String, partition: Int, offset: Long): Message {
        consumer.use {
            return find(it, TopicPartition(topic, partition), offset, offset).first()
        }
    }

    fun from(topic: String, partition: Int, offset: Long): List<Message> {
        consumer.use {
            val topicPartition = TopicPartition(topic, partition)
            val latestOffset = it
                    .endOffsets(listOf(topicPartition))[topicPartition]!! - 1

            return find(it, topicPartition, offset, latestOffset)
        }
    }

    fun last(topic: String, partition: Int, messages: Long): List<Message> {
        consumer.use {
            val topicPartition = TopicPartition(topic, partition)
            val latestOffset = it.endOffsets(listOf(topicPartition))[topicPartition]!! - 1

            return find(it, topicPartition, latestOffset - messages + 1, latestOffset)
        }
    }

    fun send(topic: String, key: String?, content: String): Pair<Message, Int> =
            producer.use {
                it.send(ProducerRecord(topic, key, content)).get()
                        .let { r -> Pair(Message(key, content, r.offset(), r.timestamp()), r.partition()) }
            }
}
