package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.models.ConsumerGroup
import kafka.rest.admin.domain.models.ConsumerGroupDetail
import kafka.rest.admin.domain.models.ConsumerGroupDetail.Companion.consumerGroupDetailOf
import kafka.rest.admin.domain.models.ConsumerGroupOffset
import kafka.rest.admin.domain.models.ConsumerGroupOffset.Companion.consumerGroupOffsetOf
import org.apache.kafka.clients.admin.AdminClient
import org.springframework.stereotype.Service

@Service
class ConsumerGroupService(val client: AdminClient) {
    fun list(): List<ConsumerGroup> =
            client.use {
                it.listConsumerGroups().all()
                        .get().map { l -> ConsumerGroup(l.groupId()) }
            }

    fun get(id: String): ConsumerGroupDetail =
            client.use {
                it.describeConsumerGroups(mutableListOf(id))
                        .describedGroups()[id]!!.get()
                        .let(::consumerGroupDetailOf)
            }

    fun offsets(id: String): List<ConsumerGroupOffset> =
            client.use {
                it.listConsumerGroupOffsets(id).partitionsToOffsetAndMetadata()
                        .get()
                        .map(::consumerGroupOffsetOf)
            }
}