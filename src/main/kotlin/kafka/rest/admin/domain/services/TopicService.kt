package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.factories.AdminClientFactory
import kafka.rest.admin.domain.models.Topic
import kafka.rest.admin.domain.models.TopicDetail
import kafka.rest.admin.domain.models.topicDetailOf
import org.springframework.stereotype.Service

@Service
class TopicService(val adminClientFactory: AdminClientFactory) {
    fun list(): List<Topic> =
            adminClientFactory.build().listTopics().listings()
                    .get().map { l -> Topic(l.name(), l.isInternal) }

    fun get(name: String?): TopicDetail =
            adminClientFactory.build()
                    .describeTopics(mutableListOf(name)).values()[name]!!.get()
                    .let(::topicDetailOf)
}
