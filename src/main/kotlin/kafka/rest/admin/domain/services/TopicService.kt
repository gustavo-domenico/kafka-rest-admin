package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.factories.AdminClientFactory
import kafka.rest.admin.domain.models.Topic
import org.springframework.stereotype.Service

@Service
class TopicService(val adminClientFactory: AdminClientFactory) {
    fun list(): List<Topic> {
        val adminClient = adminClientFactory.build()
        return adminClient.listTopics().names().get().map(::Topic)
    }

    fun get(name: String?): Topic {
        val adminClient = adminClientFactory.build()
        return Topic(adminClient
                .describeTopics(mutableListOf(name))
                .values()[name]!!
                .get().name())
    }
}