package kafka.rest.admin.infrastructure.factories

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import kafka.rest.admin.domain.models.ConsumerGroup
import kafka.rest.admin.rest.resources.ConsumerGroupResource
import org.apache.kafka.clients.admin.ConsumerGroupListing

class ConsumerGroupModelFactories {
	static def oneConsumerGroup() { new ConsumerGroup("oneConsumerGroup") }

	static def anotherConsumerGroup() { new ConsumerGroup("oneConsumerGroup") }

	static def consumerGroups() { [oneConsumerGroup(), anotherConsumerGroup()] }

	static def oneConsumerGroupResource() { new ConsumerGroupResource(oneConsumerGroup())}

	static def anotherConsumerGroupResource() { new ConsumerGroupResource(anotherConsumerGroup())}

	static def consumerGroupResources() { [oneConsumerGroupResource(), anotherConsumerGroupResource()] }

//
//	static def onTopicDetailPayload(port) {
//		def mapper = new ObjectMapper()
//		mapper.disable(MapperFeature.AUTO_DETECT_IS_GETTERS)
//		mapper.writeValueAsString(oneTopicDetail(port))
//	}
//

	static def consumerGroupsPayload() {
		def mapper = new ObjectMapper()
		mapper.disable(MapperFeature.AUTO_DETECT_IS_GETTERS)
		mapper.writeValueAsString(consumerGroups())
	}

	static def consumerGroupListings() {
		[new ConsumerGroupListing(oneConsumerGroup().id, false), new ConsumerGroupListing(anotherConsumerGroup().id, false)]
	}


}


