package kafka.rest.admin.infrastructure.configuration

import kafka.rest.admin.exceptions.ClusterConnectionException
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.common.KafkaException
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS
import org.springframework.kafka.core.KafkaAdmin
import org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST

@Configuration
class KafkaConfiguration {
    @Bean
    @Scope(SCOPE_REQUEST, proxyMode = TARGET_CLASS)
    fun client(kafkaAdmin: KafkaAdmin): AdminClient =
            try {
                val defaultConfig = kafkaAdmin
                        .config
                        .plus(arrayOf(
                                "request.timeout.ms" to 5000,
                                "retries" to 1
                        ))
                AdminClient.create(defaultConfig)!!
            } catch (e: KafkaException) {
                throw ClusterConnectionException(e.message!!)
            }

    @Bean
    @Scope(SCOPE_REQUEST, proxyMode = TARGET_CLASS)
    fun consumer(kafkaAdmin: KafkaAdmin): KafkaConsumer<String, String> =
            try {
                KafkaConsumer(mapOf(
                        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaAdmin.config[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG],
                        ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest",
                        ConsumerConfig.GROUP_ID_CONFIG to "kafka-rest-admin-consumer"),
                        StringDeserializer(), StringDeserializer())
            } catch (e: KafkaException) {
                throw ClusterConnectionException(e.message!!)
            }

    @Bean
    @Scope(SCOPE_REQUEST, proxyMode = TARGET_CLASS)
    fun producer(kafkaAdmin: KafkaAdmin): KafkaProducer<String, String> =
            try {
                val bootStrapServers = kafkaAdmin.config[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG]
                if (!bootStrapServers.toString().contains("127.0.0.1") && !bootStrapServers.toString().contains("localhost"))
                    throw ClusterConnectionException("You can just produce messages to localhost (kafka).")

                KafkaProducer(mapOf(
                        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to bootStrapServers),
                        StringSerializer(), StringSerializer())
            } catch (e: KafkaException) {
                throw ClusterConnectionException(e.message!!)
            }
}