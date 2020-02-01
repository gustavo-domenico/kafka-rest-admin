package kafka.rest.admin.rest.controllers

import kafka.rest.admin.domain.services.MessageService
import spock.lang.Specification

import static kafka.rest.admin.infrastructure.factories.MessageModelFactories.messageListResource
import static kafka.rest.admin.infrastructure.factories.MessageModelFactories.messages
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.oneTopic

class MessageControllerSpec extends Specification {
	MessageService messageService = Mock()
	MessageController messageController = new MessageController(messageService)

	def "list should return all messages from topic/partition/offset"() {
		when:
			def actual = messageController.from(oneTopic().name, 1, 0)
		then:
			1 * messageService.from(oneTopic().name, 1, 0) >> messages()
			actual == messageListResource()
	}
}
