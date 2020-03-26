package kafka.rest.admin.rest.controllers

import kafka.rest.admin.domain.services.TopicService
import kafka.rest.admin.rest.controllers.TopicController
import spock.lang.Specification

import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.oneTopic
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.oneTopicDetail
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.oneTopicDetailResource
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.topicListResource
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.topicRequest
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.topics

class TopicControllerSpec extends Specification {
	TopicService topicService = Mock()
	TopicController topicController = new TopicController(topicService)

	def "list should return all topics"() {
		when:
			def actual = topicController.list()
		then:
			1 * topicService.list() >> topics()
			actual == topicListResource()
	}

	def "get should return one specific topic"() {
		when:
			def actual = topicController.get(oneTopic().name)
		then:
			1 * topicService.get(oneTopic().name) >> oneTopicDetail()
			actual == oneTopicDetailResource()
	}

	def "add should create new topic"() {
		when:
			def actual = topicController.add(topicRequest())
		then:
			1 * topicService.add(topicRequest().name, topicRequest().partitions) >> oneTopicDetail()
			actual == oneTopicDetailResource()
	}

	def "delete should delete new topic"() {
		when:
			topicController.remove(oneTopic().name)
		then:
			1 * topicService.remove(oneTopic().name)
	}
}
