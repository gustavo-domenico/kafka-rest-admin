package kafka.rest.admin.rest.requests

data class TopicRequest(val name: String, val partitions: Int)