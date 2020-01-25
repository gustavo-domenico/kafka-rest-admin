package kafka.rest.admin.infrastructure.factories

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import kafka.rest.admin.domain.models.ClusterDetail
import kafka.rest.admin.domain.models.KafkaNode
import kafka.rest.admin.rest.resources.ClusterDetailResource
import org.apache.kafka.common.Node

class ClusterModelFactories {
	static def cluster(port = 0) { new ClusterDetail(controller(port), nodes(port)) }

	static def controllerNode() {
		new Node(1, "host", 0)
	}

	static def controller(port = 0) {
		new KafkaNode(1, "localhost", port)
	}

	static def nodesNode() {
		[new Node(1, "localhost", 3)]
	}

	static def nodes(port = 0) {
		[new KafkaNode(1, "localhost", port)]
	}

	static def clusterResource() {
		return new ClusterDetailResource(cluster())
	}

	static def clusterPayload(port) {
		def mapper = new ObjectMapper()
		mapper.disable(MapperFeature.AUTO_DETECT_IS_GETTERS)
		mapper.writeValueAsString(cluster(port))
	}
}


