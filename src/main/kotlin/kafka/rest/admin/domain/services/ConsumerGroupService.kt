package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.factories.AdminClientFactory
import kafka.rest.admin.domain.models.ConsumerGroup
import kafka.rest.admin.domain.models.ConsumerGroupDetail
import kafka.rest.admin.domain.models.ConsumerGroupDetail.Companion.consumerGroupDetailOf
import kafka.rest.admin.domain.models.ConsumerGroupOffset
import kafka.rest.admin.domain.models.ConsumerGroupOffset.Companion.consumerGroupOffsetOf
import org.springframework.stereotype.Service

@Service
class ConsumerGroupService(adminClientFactory: AdminClientFactory) : KafkaService(adminClientFactory) {
    fun list(): List<ConsumerGroup> =
            adminClient().listConsumerGroups().all()
                    .get().map { l -> ConsumerGroup(l.groupId()) }

    fun get(id: String): ConsumerGroupDetail =
            adminClient()
                    .describeConsumerGroups(mutableListOf(id)).describedGroups()[id]!!.get()
                    .let(::consumerGroupDetailOf)

    fun offsets(id: String): List<ConsumerGroupOffset> =
            adminClient().listConsumerGroupOffsets(id).partitionsToOffsetAndMetadata()
                    .get()
                    .map(::consumerGroupOffsetOf)
}