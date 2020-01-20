package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.factories.AdminClientFactory
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.DescribeTopicsResult
import org.apache.kafka.clients.admin.ListTopicsResult
import org.apache.kafka.clients.admin.TopicDescription
import spock.lang.Specification

import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.anotherTopic
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.oneTopic
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.topics
import static org.apache.kafka.common.KafkaFuture.completedFuture

class TopicServiceSpec extends Specification {
	ListTopicsResult listTopicsResult = Mock()
	DescribeTopicsResult describeTopicsResult = Mock()

	AdminClient adminClient = Mock()
	AdminClientFactory adminClientFactory = Mock()

	def topicService = new TopicService(adminClientFactory)

	def "list should get all topics"() {
		when:
			def actual = topicService.list()
		then:
			1 * adminClientFactory.build() >> adminClient
			1 * adminClient.listTopics() >> listTopicsResult
			1 * listTopicsResult.names() >> completedFuture([oneTopic().name, anotherTopic().name] as Set)

			actual == topics()
	}

	def "get should return one specific topic "() {
		when:
			def actual = topicService.get(oneTopic().name)
		then:
			1 * adminClientFactory.build() >> adminClient
			1 * adminClient.describeTopics([oneTopic().name]) >> describeTopicsResult
			1 * describeTopicsResult.values() >> [oneRandomTopicName: completedFuture(new TopicDescription(oneTopic().name, false, []))]

			actual == oneTopic()
	}
}
