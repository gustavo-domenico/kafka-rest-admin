package kafka.rest.admin.domain.models

import org.apache.kafka.clients.admin.TopicDescription

data class TopicDetail(val topic: Topic, val partitionCount: Int, val partitions: List<TopicPartition>) {
    companion object {
        fun topicDetailOf(topicDescription: TopicDescription): TopicDetail {
            return TopicDetail(
                    Topic(topicDescription.name()),
                    topicDescription.partitions().size,
                    topicDescription.partitions().mapIndexed { i, p ->
                        TopicPartition(i, p.leader().let { l -> KafkaNode(l.id(), l.host(), l.port()) })
                    }
            )
        }
    }
}
