package kafka.rest.admin.rest.controllers

import kafka.rest.admin.domain.services.ConsumerGroupService
import kafka.rest.admin.infrastructure.annotations.RestGetMapping
import kafka.rest.admin.infrastructure.routes.Routes.Companion.CONSUMER_GROUPS
import kafka.rest.admin.infrastructure.routes.Routes.Companion.OFFSETS
import kafka.rest.admin.infrastructure.routes.Routes.Companion.RESOURCE_ID
import kafka.rest.admin.rest.resources.*
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(CONSUMER_GROUPS)
class ConsumerGroupController(val consumerGroupService: ConsumerGroupService) {
    @RestGetMapping
    fun list(): ConsumerGroupListResource = ConsumerGroupListResource(consumerGroupService.list().map(::ConsumerGroupResource))

    @RestGetMapping(value = ["{$RESOURCE_ID}"])
    fun get(@PathVariable(RESOURCE_ID) id: String): ConsumerGroupDetailResource = ConsumerGroupDetailResource(consumerGroupService.get(id))

    @RestGetMapping(value = ["{$RESOURCE_ID}$OFFSETS"])
    fun offsets(@PathVariable(RESOURCE_ID) id: String): ConsumerGroupOffsetListResource =
            ConsumerGroupOffsetListResource(id, consumerGroupService.offsets(id).map(::ConsumerGroupOffsetResource))
}