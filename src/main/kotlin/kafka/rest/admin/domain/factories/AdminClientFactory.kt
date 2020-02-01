package kafka.rest.admin.domain.factories

import kafka.rest.admin.infrastructure.annotations.Factory
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.consumer.ConsumerConfig.*
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.kafka.core.KafkaAdmin

@Factory
class AdminClientFactory(val kafkaAdmin: KafkaAdmin) {
    fun buildClient(): AdminClient = AdminClient.create(kafkaAdmin.config)!!

    fun buildConsumer(): KafkaConsumer<String, String> =
            KafkaConsumer(mapOf(
                    BOOTSTRAP_SERVERS_CONFIG to kafkaAdmin.config[BOOTSTRAP_SERVERS_CONFIG],
                    AUTO_OFFSET_RESET_CONFIG to "earliest",
                    GROUP_ID_CONFIG to "kafka-rest-admin-consumer"),
                    StringDeserializer(), StringDeserializer())
}