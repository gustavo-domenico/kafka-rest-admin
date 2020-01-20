package kafka.rest.admin.domain.factories

import kafka.rest.admin.infrastructure.annotations.Factory
import org.apache.kafka.clients.admin.AdminClient
import org.springframework.kafka.core.KafkaAdmin

@Factory
class AdminClientFactory (val kafkaAdmin: KafkaAdmin) {
    fun build(): AdminClient = AdminClient.create(kafkaAdmin.config)!!
}