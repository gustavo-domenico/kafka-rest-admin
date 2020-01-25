package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.factories.AdminClientFactory
import kafka.rest.admin.domain.models.ConsumerGroup
import kafka.rest.admin.domain.models.ConsumerGroupDetail
import org.springframework.stereotype.Service

@Service
class ConsumerGroupService(val adminClientFactory: AdminClientFactory) {
    fun list(): List<ConsumerGroup> =
            adminClientFactory.build().listConsumerGroups().all()
                    .get().map { l -> ConsumerGroup(l.groupId()) }

    fun get(id: String?): ConsumerGroupDetail =
            adminClientFactory.build()
                    .describeConsumerGroups(mutableListOf(id)).describedGroups()[id]!!.get()
                    .let(ConsumerGroupDetail.Companion::consumerGroupDetailOf)
}