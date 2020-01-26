package kafka.rest.admin.rest.controllers

import kafka.rest.admin.domain.services.ConsumerGroupService
import kafka.rest.admin.infrastructure.routes.Routes.Companion.CONSUMER_GROUPS
import kafka.rest.admin.infrastructure.routes.Routes.Companion.OFFSETS
import kafka.rest.admin.infrastructure.routes.Routes.Companion.RESOURCE_ID
import kafka.rest.admin.rest.resources.ConsumerGroupDetailResource
import kafka.rest.admin.rest.resources.ConsumerGroupOffsetResource
import kafka.rest.admin.rest.resources.ConsumerGroupResource
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(CONSUMER_GROUPS)
class ConsumerGroupController(val consumerGroupService: ConsumerGroupService) {
    @GetMapping
    fun list(): List<ConsumerGroupResource> = consumerGroupService.list().map(::ConsumerGroupResource)

    @GetMapping(value = ["{$RESOURCE_ID}"], produces = [APPLICATION_JSON_VALUE])
    @ResponseBody
    fun get(@PathVariable(RESOURCE_ID) id: String?): ConsumerGroupDetailResource = ConsumerGroupDetailResource(consumerGroupService.get(id))

    @GetMapping(value = ["{$RESOURCE_ID}$OFFSETS"], produces = [APPLICATION_JSON_VALUE])
    @ResponseBody
    fun offsets(@PathVariable(RESOURCE_ID) id: String?): List<ConsumerGroupOffsetResource> = consumerGroupService.offsets(id).map(::ConsumerGroupOffsetResource)
}