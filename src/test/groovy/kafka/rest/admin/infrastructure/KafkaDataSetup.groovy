package kafka.rest.admin.infrastructure

import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.testcontainers.containers.KafkaContainer

import java.util.stream.IntStream

import static java.time.Duration.ofMillis
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.oneConsumerGroup
import static kafka.rest.admin.infrastructure.factories.MessageModelFactories.message
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.anotherTopic
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.oneTopic
import static org.apache.kafka.clients.admin.AdminClient.create
import static org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG
import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG
import static org.apache.kafka.clients.producer.ProducerConfig.CLIENT_ID_CONFIG
import static org.testcontainers.shaded.com.google.common.collect.ImmutableMap.of

class KafkaDataSetup {

	static def loadData(KafkaContainer kafkaContainer) {
		def newTopics = [
				new NewTopic(oneTopic().name, 2, (short)1),
				new NewTopic(anotherTopic().name, 1, (short)1)
		]

		def admin = create(Map.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaContainer.getBootstrapServers()))
		admin.createTopics(newTopics)

		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(
				of(
						ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaContainer.getBootstrapServers(),
						GROUP_ID_CONFIG, oneConsumerGroup().id,
						AUTO_OFFSET_RESET_CONFIG, "earliest"
				),
				new StringDeserializer(),
				new StringDeserializer()
		)

		KafkaProducer<String, String> producer = new KafkaProducer<>(
				of(
						BOOTSTRAP_SERVERS_CONFIG, kafkaContainer.getBootstrapServers(),
						CLIENT_ID_CONFIG, UUID.randomUUID().toString()
				),
				new StringSerializer(),
				new StringSerializer()
		)

		sleep(20000)

		consumer.subscribe([oneTopic().name])
		IntStream.range(0, 10).forEach({ p ->
			producer.send(new ProducerRecord<>(oneTopic().name, p % 2, message().key, message().content + p)).get()
		})
		producer.send(new ProducerRecord<>(anotherTopic().name, 0, message().key, message().content + 0)).get()
		consumer.poll(ofMillis(5000))
		consumer.commitSync()
	}
}
