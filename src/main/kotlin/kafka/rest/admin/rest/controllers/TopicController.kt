package kafka.rest.admin.rest.controllers

import kafka.rest.admin.domain.services.TopicService
import kafka.rest.admin.infrastructure.routes.Routes.Companion.RESOURCE_NAME
import kafka.rest.admin.infrastructure.routes.Routes.Companion.TOPICS
import kafka.rest.admin.rest.resources.TopicDetailResource
import kafka.rest.admin.rest.resources.TopicResource
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(TOPICS)
class TopicController(val topicService: TopicService) {
    @GetMapping
    fun list(): List<TopicResource> = topicService.list().map(::TopicResource)

    @GetMapping(value = ["{$RESOURCE_NAME}"], produces = [APPLICATION_JSON_VALUE])
    @ResponseBody
    fun get(@PathVariable(RESOURCE_NAME) name: String?): TopicDetailResource = TopicDetailResource(topicService.get(name))
}