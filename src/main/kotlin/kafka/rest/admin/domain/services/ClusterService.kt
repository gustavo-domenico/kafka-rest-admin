package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.factories.AdminClientFactory
import kafka.rest.admin.domain.models.ClusterDetail
import kafka.rest.admin.domain.models.ClusterDetail.Companion.clusterDetailOf
import org.springframework.stereotype.Service

@Service
class ClusterService(adminClientFactory: AdminClientFactory) : KafkaService(adminClientFactory) {
    fun get(): ClusterDetail =
            client().use {
                it.describeCluster().let(::clusterDetailOf)
            }
}
