package kafka.rest.admin.rest.controllers

import kafka.rest.admin.domain.services.ClusterService
import kafka.rest.admin.rest.resources.ClusterDetailResource
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class ClusterController(val clusterService: ClusterService) {
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun get(): ClusterDetailResource = ClusterDetailResource(clusterService.get())
}