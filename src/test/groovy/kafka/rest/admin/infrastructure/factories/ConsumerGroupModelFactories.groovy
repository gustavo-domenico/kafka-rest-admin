package kafka.rest.admin.infrastructure.factories

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import kafka.rest.admin.domain.models.ConsumerGroup
import kafka.rest.admin.domain.models.ConsumerGroupDetail
import kafka.rest.admin.domain.models.ConsumerGroupOffset
import kafka.rest.admin.rest.resources.ConsumerGroupDetailResource
import kafka.rest.admin.rest.resources.ConsumerGroupListResource
import kafka.rest.admin.rest.resources.ConsumerGroupOffsetResource
import kafka.rest.admin.rest.resources.ConsumerGroupResource
import org.apache.kafka.clients.admin.ConsumerGroupDescription
import org.apache.kafka.clients.admin.ConsumerGroupListing
import org.apache.kafka.clients.consumer.OffsetAndMetadata
import org.apache.kafka.common.ConsumerGroupState
import org.apache.kafka.common.Node

import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.oneTopicDetail

class ConsumerGroupModelFactories {
	static def oneConsumerGroup() { new ConsumerGroup("oneConsumerGroup") }

	static def consumerGroups() { [oneConsumerGroup()] }

	static def oneConsumerGroupResource() { new ConsumerGroupResource(oneConsumerGroup())}

	static def consumerGroupResources() { [oneConsumerGroupResource()] }

	static def oneConsumerGroupDescription() {
		new ConsumerGroupDescription(oneConsumerGroup().id, true, [], "", ConsumerGroupState.STABLE, new Node(0, "", 0))
	}

	static def consumerGroupOffsetsAndMetadata() { new OffsetAndMetadata(1, "") }

	static def consumerGroupOffset() {
		new ConsumerGroupOffset(oneTopicDetail().topic.name, oneTopicDetail().partitions.first().partition, 1, "")
	}

	static def consumerGroupOffsets() {
		[consumerGroupOffset()]
	}

	static def consumerGroupListResource() { new ConsumerGroupListResource(consumerGroupResources()) }

	static def consumerGroupOffsetsResource() {
		[new ConsumerGroupOffsetResource(consumerGroupOffset())]
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

	static def consumerGroupOffsetsPayload() {
		def mapper = new ObjectMapper()
		mapper.disable(MapperFeature.AUTO_DETECT_IS_GETTERS)
		mapper.writeValueAsString(consumerGroupOffsets())
	}

	static def consumerGroupListings() {
		[new ConsumerGroupListing(oneConsumerGroup().id, false)]
	}
}


