package kafka.rest.admin.configuration


import org.apache.kafka.clients.admin.NewTopic
import org.testcontainers.containers.KafkaContainer

import static java.util.Map.of
import static org.apache.kafka.clients.admin.AdminClient.create

class KafkaDataSetup {
	static def loadData(KafkaContainer kafkaContainer) {
		def newTopics = [new NewTopic("testTopicName", 1, (short) 1)]

		def admin = create(of("bootstrap.servers", kafkaContainer.getBootstrapServers()))
		admin.createTopics(newTopics)
	}
}
