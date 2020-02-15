package kafka.rest.admin.infrastructure.factories

import kafka.rest.admin.domain.models.Message
import kafka.rest.admin.rest.resources.MessageListResource
import kafka.rest.admin.rest.resources.MessageResource

import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.topicPartition

class MessageModelFactories {
	static def message(offset = 0) { new Message("key", "content", offset) }

	static def messages(offset = 0) { [message(offset)] }

	static messageResource() { new MessageResource(topicPartition().topic(), topicPartition().partition(), message()) }

	static messageListResource() {
		new MessageListResource([messageResource()])
	}
}


