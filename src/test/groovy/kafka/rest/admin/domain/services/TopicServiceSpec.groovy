package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.factories.ConsumerFactory
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.common.PartitionInfo
import spock.lang.Specification

class TopicServiceSpec extends Specification {
	Consumer consumer = Mock()
	ConsumerFactory consumerFactory = Mock()
	TopicService topicService = new TopicService(consumerFactory)

	def "list should get all topics"() {
		given:
			def expected = ["topic1", "topic2"] as Set
		when:
			def actual = topicService.list()
		then:
			1 * consumerFactory.build() >> consumer
			1 * consumer.listTopics() >> [topic1: null, topic2: null]

			actual == expected
	}

	def "get should return one specific topic "() {
		given:
			def expected = new PartitionInfo("topic1", 0, null, null, null)
		when:
			def actual = topicService.get("topic1")
		then:
			1 * consumerFactory.build() >> consumer
			1 * consumer.listTopics() >> [topic1: [expected], topic2: null]

			actual == [expected.toString()]
	}
}
