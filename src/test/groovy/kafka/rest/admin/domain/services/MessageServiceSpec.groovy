package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.factories.AdminClientFactory
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import spock.lang.Specification

import static kafka.rest.admin.infrastructure.factories.MessageModelFactories.message
import static kafka.rest.admin.infrastructure.factories.MessageModelFactories.messages
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.oneTopic
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.topicPartition

class MessageServiceSpec extends Specification {
	KafkaConsumer<String, String> consumer = Mock()
	AdminClientFactory adminClientFactory = Mock()
	ConsumerRecords<String, String> records = Mock()

	def messageService = new MessageService(adminClientFactory)

	def "list should return all messages from topic/partition/offset"() {
		when:
			def actual = messageService.from(oneTopic().name, 0, 0)
		then:
			1 * adminClientFactory.buildConsumer() >> consumer

			1 * consumer.assign([topicPartition()])
			1 * consumer.seek(topicPartition(), 0)
			1 * consumer.endOffsets([topicPartition()]) >> [ (topicPartition()) : 1]

			1 * records.records(_) >>  [new ConsumerRecord<String, String>(oneTopic().name, 0, 0, "key", message().content)]
			1 * consumer.poll(_) >> records

			actual == messages()
	}
}
