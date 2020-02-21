package kafka.rest.admin.domain.services


import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.DescribeClusterResult
import spock.lang.Specification

import static kafka.rest.admin.infrastructure.factories.ClusterModelFactories.cluster
import static kafka.rest.admin.infrastructure.factories.ClusterModelFactories.controllerNode
import static kafka.rest.admin.infrastructure.factories.ClusterModelFactories.nodesNode
import static org.apache.kafka.common.KafkaFuture.completedFuture

class ClusterServiceSpec extends Specification {
	DescribeClusterResult describeClusterResult = Mock()
	AdminClient client = Mock()

	def clusterService = new ClusterService(client)

	def "get should return cluster details"() {
		when:
			def actual = clusterService.get()
		then:
			1 * client.describeCluster() >> describeClusterResult
			1 * describeClusterResult.controller() >> completedFuture(controllerNode())
			1 * describeClusterResult.nodes() >> completedFuture(nodesNode())

			actual == cluster()
	}
}
