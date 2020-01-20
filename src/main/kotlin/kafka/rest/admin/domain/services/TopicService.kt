package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.factories.ConsumerFactory
import org.apache.kafka.common.PartitionInfo
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicService(val consumerFactory: ConsumerFactory) {
    fun list(): Set<String> {
        consumerFactory.build().use { consumer ->
            val map = consumer.listTopics()
            return map.keys
        }
    }

    fun get(name: String?): List<String> {
        consumerFactory.build().use { consumer ->
            val map = consumer.listTopics()
            return map[name]!!.stream().map(PartitionInfo::toString).collect(Collectors.toList())
        }
    }
}