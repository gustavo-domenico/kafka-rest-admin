package kafka.rest.admin.infrastructure

import org.testcontainers.containers.KafkaContainer

import static java.util.Map.of
import static kafka.rest.admin.infrastructure.factories.ConsumerGroupModelFactories.oneConsumerGroup
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.anotherTopic
import static kafka.rest.admin.infrastructure.factories.TopicModelFactories.oneTopic
import static org.apache.kafka.clients.admin.AdminClient.create

class KafkaDataSetup {
	static def loadData(KafkaContainer kafkaContainer) {
		def newTopics = [oneTopic().toNewTopic(), anotherTopic().toNewTopic()]
		def admin = create(of("bootstrap.servers", kafkaContainer.getBootstrapServers()))
		admin.createTopics(newTopics)

		kafkaContainer.execInContainer("/bin/sh", "-c", "/usr/bin/kafka-console-consumer --zookeeper --describe localhost:2181 --topic ${oneTopic().name} --consumer-property group.id=${oneConsumerGroup().id}");
//		kafkaContainer.execInContainer("/bin/sh", "-c", "/usr/bin/kafka-console-consumer --zookeeper localhost:2181 --topic ${oneTopic().name} --consumer-property group.id=${anotherConsumerGroup().id}");
	}
}
