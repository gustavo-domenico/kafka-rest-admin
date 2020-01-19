package kafka.rest.admin.domain;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.PartitionInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TopicService {
	public Set<String> list(KafkaConsumerFactory kafkaConsumerFactory) {
		try (Consumer<Object, Object> consumer = kafkaConsumerFactory.createConsumer()) {
			Map<String, List<PartitionInfo>> map = consumer.listTopics();
			return map.keySet();
		}
	}

	public List<String> get(KafkaConsumerFactory kafkaConsumerFactory, String name) {
		try (Consumer<Object, Object> consumer = kafkaConsumerFactory.createConsumer()) {
			Map<String, List<PartitionInfo>> map = consumer.listTopics();
			return map.get(name).stream().map(PartitionInfo::toString).collect(Collectors.toList());
		}
	}
}
