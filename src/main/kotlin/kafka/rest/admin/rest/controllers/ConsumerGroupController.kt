package kafka.rest.admin.rest.controllers

import kafka.rest.admin.domain.services.ConsumerGroupService
import kafka.rest.admin.infrastructure.annotations.RestGetMapping
import kafka.rest.admin.rest.resources.*
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/consumer-groups")
class ConsumerGroupController(val consumerGroupService: ConsumerGroupService) {
    @RestGetMapping
    fun list(): ConsumerGroupListResource = ConsumerGroupListResource(consumerGroupService.list().map(::ConsumerGroupResource))

    @RestGetMapping(value = ["/{id}"])
    fun get(@PathVariable id: String): ConsumerGroupDetailResource = ConsumerGroupDetailResource(consumerGroupService.get(id))

    @RestGetMapping(value = ["/{id}/offsets"])
    fun offsets(@PathVariable id: String): ConsumerGroupOffsetListResource =
            ConsumerGroupOffsetListResource(id, consumerGroupService.offsets(id).map(::ConsumerGroupOffsetResource))
}