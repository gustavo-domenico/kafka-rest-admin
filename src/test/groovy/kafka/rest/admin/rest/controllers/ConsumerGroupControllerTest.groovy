package kafka.rest.admin.rest.controllers

import kafka.rest.admin.domain.services.ConsumerGroupService
import spock.lang.Specification

import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.consumerGroupResources
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.consumerGroups

class ConsumerGroupControllerTest extends Specification {
	ConsumerGroupService consumerGroupService = Mock()
	ConsumerGroupController consumerGroupController = new ConsumerGroupController(consumerGroupService)

	def "list should return all consumer groups"() {
		when:
			def actual = consumerGroupController.list()
		then:
			1 * consumerGroupService.list() >> consumerGroups()
			actual == consumerGroupResources()
	}
}
