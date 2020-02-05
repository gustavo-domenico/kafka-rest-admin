package kafka.rest.admin.infrastructure.factories

import kafka.rest.admin.domain.models.Message
import kafka.rest.admin.rest.resources.MessageListResource
import kafka.rest.admin.rest.resources.MessageResource

import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.topicPartition

class MessageModelFactories {
	static def message() { new Message("key", "content") }

	static def messages() { [message()] }

	static messageResource() { new MessageResource(topicPartition().topic(), topicPartition().partition(), 0, message()) }

	static messageListResource() {
		new MessageListResource([messageResource()])
	}
}


