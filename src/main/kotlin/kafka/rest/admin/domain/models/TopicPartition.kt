package kafka.rest.admin.domain.models

data class TopicPartition(val partition: Int, val leader: KafkaNode, val lastOffset: Long)