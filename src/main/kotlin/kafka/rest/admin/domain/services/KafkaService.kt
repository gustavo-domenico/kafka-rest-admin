package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.factories.AdminClientFactory
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.consumer.ConsumerConfig.*
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.stereotype.Service

@Service
class KafkaService(val adminClientFactory: AdminClientFactory) {
    fun adminClient(): AdminClient = adminClientFactory.build()

    fun consumer(): KafkaConsumer<String, String> = KafkaConsumer(
            mapOf(
                    BOOTSTRAP_SERVERS_CONFIG to adminClientFactory.kafkaAdmin.config[BOOTSTRAP_SERVERS_CONFIG],
                    AUTO_OFFSET_RESET_CONFIG to "earliest",
                    GROUP_ID_CONFIG to "kafka-rest-admin-consumer"
            ),
            StringDeserializer(),
            StringDeserializer())
}