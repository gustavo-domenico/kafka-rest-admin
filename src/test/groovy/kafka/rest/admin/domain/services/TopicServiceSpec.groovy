package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.factories.AdminClientFactory
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.DescribeTopicsResult
import org.apache.kafka.clients.admin.ListTopicsResult
import org.apache.kafka.clients.admin.TopicDescription
import spock.lang.Specification

import static org.apache.kafka.common.KafkaFuture.completedFuture

class TopicServiceSpec extends Specification {
	ListTopicsResult listTopicsResult = Mock()
	DescribeTopicsResult describeTopicsResult = Mock()

	AdminClient adminClient = Mock()
	AdminClientFactory adminClientFactory = Mock()

	def topicService = new TopicService(adminClientFactory)

	def "list should get all topics"() {
		given:
			def expected = ["topic1", "topic2"] as Set
		when:
			def actual = topicService.list()
		then:
			1 * adminClientFactory.build() >> adminClient
			1 * adminClient.listTopics() >> listTopicsResult
			1 * listTopicsResult.names() >> completedFuture(expected)

			actual == expected
	}

	def "get should return one specific topic "() {
		given:
			def expected = new TopicDescription("topic1", false, [])
		when:
			def actual = topicService.get("topic1")
		then:
			1 * adminClientFactory.build() >> adminClient
			1 * adminClient.describeTopics(["topic1"]) >> describeTopicsResult
			1 * describeTopicsResult.values() >> [ topic1: completedFuture(expected)]

			actual == [expected.name()]
	}
}
