package kafka.rest.admin.domain.services


import kotlin.Pair
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import spock.lang.Specification

import static java.util.concurrent.CompletableFuture.completedFuture
import static kafka.rest.admin.infrastructure.factories.MessageModelFactories.message
import static kafka.rest.admin.infrastructure.factories.MessageModelFactories.messageRequest
import static kafka.rest.admin.infrastructure.factories.MessageModelFactories.messages
import static kafka.rest.admin.infrastructure.factories.MessageModelFactories.producerRecord
import static kafka.rest.admin.infrastructure.factories.MessageModelFactories.recordMetadata
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.oneTopic
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.topicPartition

class MessageServiceSpec extends Specification {
	KafkaConsumer<String, String> consumer = Mock()
	KafkaProducer<String, String> producer = Mock()
	ConsumerRecords<String, String> records = Mock()

	MessageService messageService = new MessageService(consumer, producer)

	def "offset should return one message from specific topic/partition/offset"() {
		when:
			def actual = messageService.offset(oneTopic().name, 0, 0)
		then:
			1 * consumer.assign([topicPartition()])
			1 * consumer.seek(topicPartition(), 0)

			1 * records.records(topicPartition()) >>  [new ConsumerRecord<String, String>(oneTopic().name, 0, 0, message().key, message().content)]
			1 * consumer.poll(_) >> records

			actual == message()
	}

	def "from should return all messages from topic/partition/offset"() {
		when:
			def actual = messageService.from(oneTopic().name, 0, 0)
		then:
			1 * consumer.assign([topicPartition()])
			1 * consumer.seek(topicPartition(), 0)
			1 * consumer.endOffsets([topicPartition()]) >> [ (topicPartition()) : 1]

			1 * records.records(topicPartition()) >>  [new ConsumerRecord<String, String>(oneTopic().name, 0, 0, message().key, message().content)]
			1 * consumer.poll(_) >> records

			actual == messages()
	}

	def "last should return last messages from topic/partition"() {
		when:
			def actual = messageService.last(oneTopic().name, 0, 1)
		then:
			1 * consumer.assign([topicPartition()])
			1 * consumer.seek(topicPartition(), 4)
			1 * consumer.endOffsets([topicPartition()]) >> [ (topicPartition()) : 5]

			1 * records.records(topicPartition()) >>  [new ConsumerRecord<String, String>(oneTopic().name, 0, 4, message().key, message().content)]
			1 * consumer.poll(_) >> records

			actual == messages(4)
	}

	def "send should create new message and return it"() {
		when:
			def actual = messageService.send(oneTopic().name, messageRequest().key, messageRequest().content)
		then:
			1 * producer.send(producerRecord()) >> completedFuture(recordMetadata())

			actual == new Pair(message(), 0)
	}
}
