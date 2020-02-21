package kafka.rest.admin.infrastructure.factories

import kafka.rest.admin.domain.models.Message
import kafka.rest.admin.rest.requests.MessageRequest
import kafka.rest.admin.rest.resources.MessageListResource
import kafka.rest.admin.rest.resources.MessageResource
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata

import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.oneTopic
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.topicPartition

class MessageModelFactories {
	static def message(offset = 0) { new Message("key", "content", offset) }

	static def messageRequest() { new MessageRequest(message().key, message().content) }

	static def messageContent() { "content6" }

	static def messages(offset = 0) { [message(offset)] }

	static messageResource() { new MessageResource(topicPartition().topic(), topicPartition().partition(), message()) }

	static messageListResource() {
		new MessageListResource([messageResource()])
	}

	static producerRecord() {
		new ProducerRecord<String, String>(oneTopic().name, messageRequest().key, messageRequest().content)
	}

	static recordMetadata() {
		new RecordMetadata(topicPartition(), 0, 0,-1,112313,1,1)
	}
}


