package kafka.rest.admin.rest.controllers

import kafka.rest.admin.domain.services.ConsumerGroupService
import spock.lang.Specification

import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.consumerGroupListResource
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.consumerGroupOffsetListResource
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.consumerGroupOffsets
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.consumerGroups
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.oneConsumerGroup
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.oneConsumerGroupDetail
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.oneConsumerGroupDetailResource

class ConsumerGroupControllerSpec extends Specification {
	ConsumerGroupService consumerGroupService = Mock()
	ConsumerGroupController consumerGroupController = new ConsumerGroupController(consumerGroupService)

	def "list should return all consumer groups"() {
		when:
			def actual = consumerGroupController.list()
		then:
			1 * consumerGroupService.list() >> consumerGroups()
			actual == consumerGroupListResource()
	}

	def "get should return one specific consumer group"() {
		when:
			def actual = consumerGroupController.get(oneConsumerGroup().id)
		then:
			1 * consumerGroupService.get(oneConsumerGroup().id) >> oneConsumerGroupDetail()
			actual == oneConsumerGroupDetailResource()
	}

	def "offsets should return consumer group offsets"() {
		when:
			def actual = consumerGroupController.offsets(oneConsumerGroup().id)
		then:
			1 * consumerGroupService.offsets(oneConsumerGroup().id) >> consumerGroupOffsets()
			actual == consumerGroupOffsetListResource()
	}
}
