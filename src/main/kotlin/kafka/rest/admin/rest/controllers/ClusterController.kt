package kafka.rest.admin.rest.controllers

import kafka.rest.admin.domain.services.ClusterService
import kafka.rest.admin.infrastructure.annotations.RestGetMapping
import kafka.rest.admin.rest.resources.ClusterDetailResource
import org.springframework.web.bind.annotation.RestController

@RestController
class ClusterController(val clusterService: ClusterService) {
    @RestGetMapping
    fun get(): ClusterDetailResource = ClusterDetailResource(clusterService.get())
}