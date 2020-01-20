package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.factories.AdminClientFactory
import org.springframework.stereotype.Service

@Service
class TopicService(val adminClientFactory: AdminClientFactory) {
    fun list(): Set<String> {
        val adminClient = adminClientFactory.build()
        return adminClient.listTopics().names().get()
    }

    fun get(name: String?): List<String> {
        val adminClient = adminClientFactory.build()
        val kafkaFuture = adminClient.describeTopics(mutableListOf(name)).values()[name]
        return listOf(kafkaFuture!!.get().name())
    }
}