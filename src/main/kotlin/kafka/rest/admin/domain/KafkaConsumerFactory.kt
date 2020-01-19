package kafka.rest.admin.domain

import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import java.util.*

class KafkaConsumerFactory(private val internalKafkaConsumerFactory: DefaultKafkaConsumerFactory<Any, Any>) {
    fun createConsumer(): Consumer<Any, Any> = internalKafkaConsumerFactory.createConsumer()

    companion object {
        fun create(bootstrapServers: String): KafkaConsumerFactory {
            val props: MutableMap<String, Any> = HashMap()
            props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
            props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
            props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
            return KafkaConsumerFactory(DefaultKafkaConsumerFactory(props))
        }
    }

}