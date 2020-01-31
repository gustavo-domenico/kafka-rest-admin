package kafka.rest.admin.infrastructure.factories

import kafka.rest.admin.domain.models.Message
import kafka.rest.admin.rest.resources.MessageListResource
import kafka.rest.admin.rest.resources.MessageResource

class MessageModelFactories {
	static def message() { new Message("RandomContent") }

	static def messages() { [message()] }

	static messageResource() { new MessageResource(message()) }

	static messageListResource() {
		new MessageListResource([messageResource()])
	}
}


