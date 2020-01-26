package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.factories.AdminClientFactory
import org.apache.kafka.clients.admin.AdminClient
import org.springframework.stereotype.Service

@Service
class KafkaService(val adminClientFactory: AdminClientFactory) {
    fun adminClient() : AdminClient {
       return adminClientFactory.build()
   }
}