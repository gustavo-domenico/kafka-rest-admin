package kafka.rest.admin.domain.services

import kafka.rest.admin.domain.models.ClusterDetail
import kafka.rest.admin.domain.models.ClusterDetail.Companion.clusterDetailOf
import org.apache.kafka.clients.admin.AdminClient
import org.springframework.stereotype.Service

@Service
class ClusterService(val client: AdminClient) {
    fun get(): ClusterDetail =
            client.use {
                it.describeCluster().let(::clusterDetailOf)
            }
}
