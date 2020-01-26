package kafka.rest.admin.domain.models

import org.apache.kafka.clients.consumer.OffsetAndMetadata
import org.apache.kafka.common.TopicPartition
import kotlin.collections.Map.Entry

data class ConsumerGroupOffset(val topicName: String, val partition: Int, val offset: Long, val metadata: String) {
    companion object {
        fun consumerGroupOffsetOf(entry: Entry<TopicPartition, OffsetAndMetadata>): ConsumerGroupOffset {
            return ConsumerGroupOffset(entry.key.topic(), entry.key.partition(), entry.value.offset(), entry.value.metadata())
        }
    }
}
