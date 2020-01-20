package kafka.rest.admin.controllers

import kafka.rest.admin.domain.models.Topic
import kafka.rest.admin.domain.services.TopicService
import spock.lang.Specification

class TopicControllerSpec extends Specification {
	TopicService topicService = Mock()
	TopicController topicController = new TopicController(topicService)

	def "list should return all topics"() {
		given:
			def expected = [new Topic("topic1"), new Topic("topic2")]
		when:
			def actual = topicController.list()
		then:
			1 * topicService.list() >> expected
			actual == expected
	}

	def "get should return one specific topic"() {
		given:
			def expected = new Topic("topic1")
		when:
			def actual = topicController.get(expected.name)
		then:
			1 * topicService.get(expected.name) >> expected
			actual == expected
	}
}
