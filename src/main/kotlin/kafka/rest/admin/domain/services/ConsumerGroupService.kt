package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.factories.AdminClientFactory
import kafka.rest.admin.domain.models.ConsumerGroup
import org.springframework.stereotype.Service

@Service
class ConsumerGroupService(val adminClientFactory: AdminClientFactory) {
    fun list(): List<ConsumerGroup> =
            adminClientFactory.build().listConsumerGroups().all()
                    .get().map { l -> ConsumerGroup(l.groupId()) }

}
