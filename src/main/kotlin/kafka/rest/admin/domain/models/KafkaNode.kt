package kafka.rest.admin.domain.models

import org.apache.kafka.common.Node

data class KafkaNode(val id: Int, val host: String, val port: Int) {
    companion object {
        fun kafkaNodeOf(node: Node): KafkaNode =
                KafkaNode(node.id(), node.host(), node.port())

    }
}
