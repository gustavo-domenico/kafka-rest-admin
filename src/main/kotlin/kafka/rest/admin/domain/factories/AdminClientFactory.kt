package kafka.rest.admin.domain.factories

import kafka.rest.admin.exceptions.ClusterConnectionException
import kafka.rest.admin.infrastructure.annotations.Factory
import kafka.rest.admin.infrastructure.logging.Log
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.consumer.ConsumerConfig.*
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.KafkaException
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.kafka.core.KafkaAdmin

@Factory
class AdminClientFactory(val kafkaAdmin: KafkaAdmin) {
    companion object : Log

    fun buildClient(): AdminClient =
            try {
                val defaultConfig = kafkaAdmin
                        .config
                        .plus(arrayOf(
                                "request.timeout.ms" to 5000,
                                "retries" to 1
                        ))
                AdminClient.create(defaultConfig)!!
            } catch (e: KafkaException) {
                logger().error(e.message, e)
                throw ClusterConnectionException(e.message!!)
            }

    fun buildConsumer(): KafkaConsumer<String, String> =
            try {
                KafkaConsumer(mapOf(
                        BOOTSTRAP_SERVERS_CONFIG to kafkaAdmin.config[BOOTSTRAP_SERVERS_CONFIG],
                        AUTO_OFFSET_RESET_CONFIG to "earliest",
                        GROUP_ID_CONFIG to "kafka-rest-admin-consumer"),
                        StringDeserializer(), StringDeserializer())
            } catch (e: KafkaException) {
                logger().error(e.message, e)
                throw ClusterConnectionException(e.message!!)
            }
}