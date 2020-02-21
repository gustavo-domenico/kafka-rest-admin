package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.factories.AdminClientFactory
import org.springframework.stereotype.Service

@Service
class KafkaService(val adminClientFactory: AdminClientFactory) {
    fun client() = adminClientFactory.buildClient()
    fun consumer() = adminClientFactory.buildConsumer()
    fun producer() = adminClientFactory.buildProducer()
}