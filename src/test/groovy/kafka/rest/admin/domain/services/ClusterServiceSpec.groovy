package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.factories.AdminClientFactory
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.DescribeClusterResult
import spock.lang.Specification

import static kafka.rest.admin.infrastructure.factories.ClusterModelFactories.cluster
import static kafka.rest.admin.infrastructure.factories.ClusterModelFactories.controllerNode
import static kafka.rest.admin.infrastructure.factories.ClusterModelFactories.nodesNode
import static org.apache.kafka.common.KafkaFuture.completedFuture

class ClusterServiceSpec extends Specification {
	DescribeClusterResult describeClusterResult = Mock()
	AdminClient adminClient = Mock()
	AdminClientFactory adminClientFactory = Mock()

	def clusterService = new ClusterService(adminClientFactory)

	def "get should return cluster information"() {
		when:
			def actual = clusterService.get()
		then:
			1 * adminClientFactory.buildClient() >> adminClient
			1 * adminClient.describeCluster() >> describeClusterResult
			1 * describeClusterResult.controller() >> completedFuture(controllerNode())
			1 * describeClusterResult.nodes() >> completedFuture(nodesNode())

			actual == cluster()
	}
}
