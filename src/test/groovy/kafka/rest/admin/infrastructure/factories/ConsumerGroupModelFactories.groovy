package kafka.rest.admin.infrastructure.factories

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import kafka.rest.admin.domain.models.ConsumerGroup
import kafka.rest.admin.domain.models.ConsumerGroupDetail
import kafka.rest.admin.rest.resources.ConsumerGroupDetailResource
import kafka.rest.admin.rest.resources.ConsumerGroupResource
import org.apache.kafka.clients.admin.ConsumerGroupDescription
import org.apache.kafka.clients.admin.ConsumerGroupListing
import org.apache.kafka.common.ConsumerGroupState
import org.apache.kafka.common.Node

class ConsumerGroupModelFactories {
	static def oneConsumerGroup() { new ConsumerGroup("oneConsumerGroup") }

	static def consumerGroups() { [oneConsumerGroup()] }

	static def oneConsumerGroupResource() { new ConsumerGroupResource(oneConsumerGroup())}

	static def consumerGroupResources() { [oneConsumerGroupResource()] }

	static def oneConsumerGroupDescription() {
		new ConsumerGroupDescription(oneConsumerGroup().id, true, [], "", ConsumerGroupState.STABLE, new Node(0, "", 0))
	}

	static def oneConsumerGroupDetail() {
		new ConsumerGroupDetail(oneConsumerGroup(), ConsumerGroupState.STABLE.toString())
	}

	static def oneConsumerGroupDetailResource() {
		return new ConsumerGroupDetailResource(oneConsumerGroupDetail())
	}

	static def oneConsumerGroupDetailPayload() {
		def mapper = new ObjectMapper()
		mapper.disable(MapperFeature.AUTO_DETECT_IS_GETTERS)
		mapper.writeValueAsString(oneConsumerGroupDetail())
	}


	static def consumerGroupsPayload() {
		def mapper = new ObjectMapper()
		mapper.disable(MapperFeature.AUTO_DETECT_IS_GETTERS)
		mapper.writeValueAsString(consumerGroups())
	}

	static def consumerGroupListings() {
		[new ConsumerGroupListing(oneConsumerGroup().id, false)]
	}
}


