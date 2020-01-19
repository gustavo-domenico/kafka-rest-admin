package kafka.rest.admin.controllers

import kafka.rest.admin.domain.services.TopicService
import spock.lang.Specification

class TopicControllerSpec extends Specification {
	TopicService topicService = Mock()
	TopicController topicController = new TopicController(topicService)

	def "list should return all topics"() {
		given:
			def expected = ["topic1", "topic2"] as Set
		when:
			def actual = topicController.list()
		then:
			1 * topicService.list() >> expected
			actual == expected
	}

	def "get should return one specific topic"() {
		given:
			def expected = ["topic1Info", "topic1Info"]
			def topic = "topic"
		when:
			def actual = topicController.get(topic)
		then:
			1 * topicService.get(topic) >> expected
			actual == expected
	}
}
