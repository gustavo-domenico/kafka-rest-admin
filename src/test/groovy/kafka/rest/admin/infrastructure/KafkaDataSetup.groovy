package kafka.rest.admin.infrastructure

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.testcontainers.containers.KafkaContainer

import java.time.Duration

import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.oneConsumerGroup
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.anotherTopic
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.oneTopic
import static org.apache.kafka.clients.admin.AdminClient.create
import static org.testcontainers.shaded.com.google.common.collect.ImmutableMap.of

class KafkaDataSetup {
	static def loadData(KafkaContainer kafkaContainer) {
		def newTopics = [oneTopic().toNewTopic(), anotherTopic().toNewTopic()]
		def admin = create(Map.of("bootstrap.servers", kafkaContainer.getBootstrapServers()))
		admin.createTopics(newTopics)

		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(
				of(
						ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaContainer.getBootstrapServers(),
						ConsumerConfig.GROUP_ID_CONFIG, oneConsumerGroup().id,
						ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"
				),
				new StringDeserializer(),
				new StringDeserializer()
		)

		KafkaProducer<String, String> producer = new KafkaProducer<>(
				of(
						ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaContainer.getBootstrapServers(),
						ProducerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString()
				),
				new StringSerializer(),
				new StringSerializer()
		)

		consumer.subscribe([oneTopic().name])
		producer.send(new ProducerRecord<>(oneTopic().name, 0, "testcontainers", "content")).get()
		consumer.poll(Duration.ofMillis(5000))
		consumer.commitSync()
	}
}
