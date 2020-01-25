package kafka.rest.admin.rest.controllers

import kafka.rest.admin.domain.services.ConsumerGroupService
import spock.lang.Specification

import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.consumerGroupResources
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.consumerGroups
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.oneConsumerGroup
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.oneConsumerGroupDetail
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.oneConsumerGroupDetailResource

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

	def "get should return one specific consumer group"() {
		when:
			def actual = consumerGroupController.get(oneConsumerGroup().id)
		then:
			1 * consumerGroupService.get(oneConsumerGroup().id) >> oneConsumerGroupDetail()
			actual == oneConsumerGroupDetailResource()
	}
}
