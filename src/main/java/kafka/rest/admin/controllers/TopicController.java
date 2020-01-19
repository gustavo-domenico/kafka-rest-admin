package kafka.rest.admin.controllers;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.PartitionInfo;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topics")
public class TopicController {
	@GetMapping
	public Set<String> list(@ModelAttribute ConsumerFactory<String, String> consumerFactory) {
		try (Consumer<String, String> consumer = consumerFactory.createConsumer()) {
			Map<String, List<PartitionInfo>> map = consumer.listTopics();
			return map.keySet();
		}
	}

	@GetMapping(value = "{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<String> get(@ModelAttribute ConsumerFactory<String, String> consumerFactory, @PathVariable("name") String name) {
		try (Consumer<String, String> consumer = consumerFactory.createConsumer()) {
			Map<String, List<PartitionInfo>> map = consumer.listTopics();
			return  map.get(name).stream().map(PartitionInfo::toString).collect(Collectors.toList());
		}
	}
}
