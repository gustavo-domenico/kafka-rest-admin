package kafka.rest.admin.infrastructure.factories

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import kafka.rest.admin.domain.models.KafkaNode
import kafka.rest.admin.domain.models.Topic
import kafka.rest.admin.domain.models.TopicDetail
import kafka.rest.admin.domain.models.TopicPartition
import kafka.rest.admin.rest.resources.TopicDetailResource
import kafka.rest.admin.rest.resources.TopicListResource
import kafka.rest.admin.rest.resources.TopicResource
import org.apache.kafka.clients.admin.TopicDescription
import org.apache.kafka.clients.admin.TopicListing
import org.apache.kafka.common.Node
import org.apache.kafka.common.TopicPartitionInfo

class TopicModelFactories {
	static def oneTopic() { new Topic("oneRandomTopicName") }

	static def oneTopicResource() { new TopicResource(oneTopic()) }

	static def oneTopicDetail(port = 0) {
		new TopicDetail(new Topic("oneRandomTopicName"), 1, [
				new TopicPartition(0, new KafkaNode(1, "localhost", port))
		])
	}

	static def oneTopicDetailResource() { new TopicDetailResource(oneTopicDetail()) }

	static def oneTopicDescription() {
		new TopicDescription(oneTopic().name, false,
				[new TopicPartitionInfo(0, new Node(1, "localhost", 0), [], [])])
	}

	static def anotherTopic() { new Topic("anotherRandomTopicName") }

	static def anotherTopicResource() { new TopicResource(anotherTopic()) }

	static def topics() { [oneTopic(), anotherTopic()] }

	static def topicsResources() { [oneTopicResource(), anotherTopicResource()] }

	static def topicListResource() { new TopicListResource(topicsResources()) }

	static def onTopicDetailPayload(port) {
		def mapper = new ObjectMapper()
		mapper.disable(MapperFeature.AUTO_DETECT_IS_GETTERS)
		mapper.writeValueAsString(oneTopicDetail(port))
	}

	static def topicListings() {
		[new TopicListing(oneTopic().name, false), new TopicListing(anotherTopic().name, false)]
	}
}


