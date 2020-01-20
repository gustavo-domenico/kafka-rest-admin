package kafka.rest.admin.infrastructure.factories

import com.fasterxml.jackson.databind.ObjectMapper
import kafka.rest.admin.domain.models.Topic

class TopicModelFactories {
	static def oneTopic() { new Topic("oneRandomTopicName") }

	static def anotherTopic() { new Topic("anotherRandomTopicName") }

	static def topics() { [oneTopic(), anotherTopic()] }

	static def onTopicPayload() { new ObjectMapper().writeValueAsString(oneTopic()) }

	static def topicsPayload() { new ObjectMapper().writeValueAsString(topics()) }
}


