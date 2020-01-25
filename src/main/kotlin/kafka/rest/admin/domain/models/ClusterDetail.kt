package kafka.rest.admin.domain.models

import kafka.rest.admin.domain.models.KafkaNode.Companion.kafkaNodeOf
import org.apache.kafka.clients.admin.DescribeClusterResult

data class ClusterDetail(val controller: KafkaNode, val nodes: List<KafkaNode>) {
    companion object {
        fun clusterDetailOf(clusterDescription: DescribeClusterResult): ClusterDetail {
            val controller = clusterDescription.controller().get()
            return ClusterDetail(kafkaNodeOf(controller),
                    clusterDescription.nodes().get().map(::kafkaNodeOf)
            )
        }
    }
}
