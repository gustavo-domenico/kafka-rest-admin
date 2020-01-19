package kafka.rest.admin.domain;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

public class KafkaConsumerFactory {
	private DefaultKafkaConsumerFactory<Object, Object> internalKafkaConsumerFactory;

	public KafkaConsumerFactory(DefaultKafkaConsumerFactory<Object, Object> internalKafkaConsumerFactory) {
		this.internalKafkaConsumerFactory = internalKafkaConsumerFactory;
	}

	public static KafkaConsumerFactory create(String bootstrapServers) {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return new KafkaConsumerFactory(new DefaultKafkaConsumerFactory<>(props));
	}

	public Consumer<Object, Object> createConsumer() {
		return internalKafkaConsumerFactory.createConsumer();
	}
}
