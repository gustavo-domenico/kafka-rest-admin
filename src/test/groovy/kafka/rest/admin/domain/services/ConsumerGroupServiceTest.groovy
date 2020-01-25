package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.factories.AdminClientFactory
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.DescribeConsumerGroupsResult
import org.apache.kafka.clients.admin.ListConsumerGroupsResult
import spock.lang.Specification

import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.consumerGroupListings
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.consumerGroups
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.oneConsumerGroup
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.oneConsumerGroupDescription
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.oneConsumerGroupDetail
import static org.apache.kafka.common.KafkaFuture.completedFuture

class ConsumerGroupServiceTest extends Specification {
	ListConsumerGroupsResult listConsumerGroupsResult = Mock()
	DescribeConsumerGroupsResult describeConsumerGroupsResult = Mock()
	AdminClient adminClient = Mock()
	AdminClientFactory adminClientFactory = Mock()

	def consumerGroupService = new ConsumerGroupService(adminClientFactory)

	def "list should get all consumer groups"() {
		when:
			def actual = consumerGroupService.list()
		then:
			1 * adminClientFactory.build() >> adminClient
			1 * adminClient.listConsumerGroups() >> listConsumerGroupsResult
			1 * listConsumerGroupsResult.all() >> completedFuture(consumerGroupListings())

			actual == consumerGroups()
	}

	def "get should return one specific consumer group"() {
		when:
			def actual = consumerGroupService.get(oneConsumerGroup().id)
		then:
			1 * adminClientFactory.build() >> adminClient
			1 * adminClient.describeConsumerGroups([oneConsumerGroup().id]) >> describeConsumerGroupsResult
			1 * describeConsumerGroupsResult.describedGroups() >> [oneConsumerGroup: completedFuture(oneConsumerGroupDescription())]

			actual == oneConsumerGroupDetail()
	}
}
