package kafka.rest.admin.domain.models

import org.apache.kafka.clients.admin.ConsumerGroupDescription

data class ConsumerGroupDetail(val consumerGroup: ConsumerGroup, val state: String) {
    companion object {
        fun consumerGroupDetailOf(consumerGroupDescription: ConsumerGroupDescription): ConsumerGroupDetail =
                ConsumerGroupDetail(
                        ConsumerGroup(consumerGroupDescription.groupId()),
                        consumerGroupDescription.state().toString())
    }
}
