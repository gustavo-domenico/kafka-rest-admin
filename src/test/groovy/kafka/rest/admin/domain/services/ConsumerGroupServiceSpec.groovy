package kafka.rest.admin.domain.services


import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.DescribeConsumerGroupsResult
import org.apache.kafka.clients.admin.ListConsumerGroupOffsetsResult
import org.apache.kafka.clients.admin.ListConsumerGroupsResult
import org.apache.kafka.common.TopicPartition
import spock.lang.Specification

import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.consumerGroupListings
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.consumerGroupOffsets
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.consumerGroupOffsetsAndMetadata
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.consumerGroups
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.oneConsumerGroup
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.oneConsumerGroupDescription
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.oneConsumerGroupDetail
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.oneTopicDetail
import static org.apache.kafka.common.KafkaFuture.completedFuture

class ConsumerGroupServiceSpec extends Specification {
	ListConsumerGroupsResult listConsumerGroupsResult = Mock()
	DescribeConsumerGroupsResult describeConsumerGroupsResult = Mock()
	ListConsumerGroupOffsetsResult listConsumerGroupOffsetsResult = Mock()
	AdminClient client = Mock()

	def consumerGroupService = new ConsumerGroupService(client)

	def "list should return consumer groups"() {
		when:
			def actual = consumerGroupService.list()
		then:
			1 * client.listConsumerGroups() >> listConsumerGroupsResult
			1 * listConsumerGroupsResult.all() >> completedFuture(consumerGroupListings())

			actual == consumerGroups()
	}

	def "get should return consumer group details"() {
		when:
			def actual = consumerGroupService.get(oneConsumerGroup().id)
		then:
			1 * client.describeConsumerGroups([oneConsumerGroup().id]) >> describeConsumerGroupsResult
			1 * describeConsumerGroupsResult.describedGroups() >> [oneConsumerGroup: completedFuture(oneConsumerGroupDescription())]

			actual == oneConsumerGroupDetail()
	}

	def "offset should return consumer group offsets"() {
		when:
			def actual = consumerGroupService.offsets(oneConsumerGroup().id)
		then:
			1 * client.listConsumerGroupOffsets(oneConsumerGroup().id) >> listConsumerGroupOffsetsResult

			1 * listConsumerGroupOffsetsResult.partitionsToOffsetAndMetadata() >> completedFuture(
					[(new TopicPartition(oneTopicDetail().topic.name, oneTopicDetail().partitions.first().partition)): consumerGroupOffsetsAndMetadata()])

			actual == consumerGroupOffsets()
	}
}
