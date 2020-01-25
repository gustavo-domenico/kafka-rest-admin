package kafka.rest.admin.rest.controllers

import kafka.rest.admin.domain.services.ConsumerGroupService
import kafka.rest.admin.infrastructure.routes.Routes.Companion.CONSUMER_GROUPS
import kafka.rest.admin.rest.resources.ConsumerGroupResource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(CONSUMER_GROUPS)
class ConsumerGroupController(val consumerGroupService: ConsumerGroupService) {
    @GetMapping
    fun list(): List<ConsumerGroupResource> = consumerGroupService.list().map(::ConsumerGroupResource)

//    @GetMapping(value = ["{$RESOURCE_NAME}"], produces = [APPLICATION_JSON_VALUE])
//    @ResponseBody
//    fun get(@PathVariable(RESOURCE_NAME) name: String?): TopicDetailResource = TopicDetailResource(topicService.get(name))
}