package kafka.rest.admin.configuration

import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.NewTopic
import org.testcontainers.containers.KafkaContainer

class KafkaDataSetup {
	static def loadData(KafkaContainer kafkaContainer) {
		def newTopics = [new NewTopic("testTopicName", 1, (short) 1)]

		def admin = AdminClient.create(Map.of("bootstrap.servers", kafkaContainer.getBootstrapServers()))
		admin.createTopics(newTopics)
	}
}
