package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.factories.AdminClientFactory
import kafka.rest.admin.domain.models.Topic
import kafka.rest.admin.domain.models.TopicDetail
import kafka.rest.admin.domain.models.TopicDetail.Companion.topicDetailOf
import org.springframework.stereotype.Service

@Service
class TopicService(adminClientFactory: AdminClientFactory) : KafkaService(adminClientFactory) {
    fun list(): List<Topic> =
            client().listTopics().listings()
                    .get().map { l -> Topic(l.name()) }

    fun get(name: String): TopicDetail =
            client().describeTopics(mutableListOf(name)).values()[name]!!.get()
                    .let(::topicDetailOf)
}
