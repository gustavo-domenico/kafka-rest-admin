package kafka.rest.admin.infrastructure.factories


import kafka.rest.admin.domain.models.ClusterDetail
import kafka.rest.admin.domain.models.KafkaNode
import kafka.rest.admin.rest.resources.ClusterDetailResource
import org.apache.kafka.common.Node

class ClusterModelFactories {
	static def cluster(port = 0) { new ClusterDetail(controller(port), nodes(port)) }

	static def controllerNode() {
		new Node(1, "localhost", 0)
	}

	static def controller(port = 0) {
		new KafkaNode(1, "localhost", port)
	}

	static def nodesNode() {
		[new Node(1, "localhost", 0)]
	}

	static def nodes(port = 0) {
		[new KafkaNode(1, "localhost", port)]
	}

	static def clusterResource() {
		return new ClusterDetailResource(cluster())
	}
}


