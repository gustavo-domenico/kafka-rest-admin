package kafka.rest.admin.domain.models

data class TopicDetail(val topic: Topic, val partitionCount: Int, val partitions: List<TopicPartition>)
