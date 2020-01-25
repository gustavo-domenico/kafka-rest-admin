package kafka.rest.admin.rest.controllers

import kafka.rest.admin.domain.services.ClusterService
import spock.lang.Specification

import static kafka.rest.admin.infrastructure.factories.ClusterModelFactories.cluster
import static kafka.rest.admin.infrastructure.factories.ClusterModelFactories.clusterResource

class ClusterControllerSpec extends Specification {
	ClusterService clusterService = Mock()
	ClusterController clusterController = new ClusterController(clusterService)

	def "get should return cluster information"() {
		when:
			def actual = clusterController.get()
		then:
			1 * clusterService.get() >> cluster()
			actual == clusterResource()
	}
}
