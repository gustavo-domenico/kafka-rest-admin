package kafka.rest.admin.domain

import org.apache.kafka.common.PartitionInfo
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicService {
    fun list(kafkaConsumerFactory: KafkaConsumerFactory): Set<String> {
        kafkaConsumerFactory.createConsumer().use { consumer ->
            val map = consumer.listTopics()
            return map.keys
        }
    }

    fun get(kafkaConsumerFactory: KafkaConsumerFactory, name: String?): List<String> {
        kafkaConsumerFactory.createConsumer().use { consumer ->
            val map = consumer.listTopics()
            return map[name]!!.stream().map { obj: PartitionInfo -> obj.toString() }.collect(Collectors.toList())
        }
    }
}