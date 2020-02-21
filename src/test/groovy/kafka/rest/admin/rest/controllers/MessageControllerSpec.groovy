package kafka.rest.admin.rest.controllers

import kafka.rest.admin.domain.services.MessageService
import kotlin.Pair
import spock.lang.Specification

import static kafka.rest.admin.infrastructure.factories.MessageModelFactories.message
import static kafka.rest.admin.infrastructure.factories.MessageModelFactories.messageListResource
import static kafka.rest.admin.infrastructure.factories.MessageModelFactories.messageRequest
import static kafka.rest.admin.infrastructure.factories.MessageModelFactories.messageResource
import static kafka.rest.admin.infrastructure.factories.MessageModelFactories.messages
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.oneTopic
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.topicPartition
import static org.springframework.http.ResponseEntity.ok

class MessageControllerSpec extends Specification {
	MessageService messageService = Mock()
	MessageController messageController = new MessageController(messageService)

	def "content should the message content"() {
		when:
			def actual = messageController.raw(topicPartition().topic(), topicPartition().partition(), 0)
		then:
			1 * messageService.offset(topicPartition().topic(), topicPartition().partition(), 0) >> message()
			actual == ok(message().content)
	}

	def "offset should the exactly message from topic/partition/offset"() {
		when:
			def actual = messageController.offset(topicPartition().topic(), topicPartition().partition(), 0)
		then:
			1 * messageService.offset(topicPartition().topic(), topicPartition().partition(), 0) >> message()
			actual == messageResource()
	}

	def "from should return all messages from topic/partition/offset"() {
		when:
			def actual = messageController.from(topicPartition().topic(), topicPartition().partition(), 0)
		then:
			1 * messageService.from(topicPartition().topic(), topicPartition().partition(), 0) >> messages()
			actual == messageListResource()
	}

	def "last should return last messages from topic/partition"() {
		when:
			def actual = messageController.last(topicPartition().topic(), topicPartition().partition(), 1)
		then:
			1 * messageService.last(topicPartition().topic(), topicPartition().partition(), 1) >> messages()
			actual == messageListResource()
	}

	def "send should create new message and return it"() {
		when:
			def actual = messageController.send(oneTopic().name, messageRequest())
		then:
			1 * messageService.send(oneTopic().name, messageRequest().key, messageRequest().content) >> new Pair(message(), 0)
			actual == messageResource()
	}
}
