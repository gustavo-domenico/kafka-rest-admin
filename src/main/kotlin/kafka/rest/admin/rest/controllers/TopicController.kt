package kafka.rest.admin.rest.controllers

import kafka.rest.admin.domain.services.TopicService
import kafka.rest.admin.infrastructure.annotations.RestGetMapping
import kafka.rest.admin.rest.resources.TopicDetailResource
import kafka.rest.admin.rest.resources.TopicListResource
import kafka.rest.admin.rest.resources.TopicResource
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topics")
class TopicController(val topicService: TopicService) {
    @RestGetMapping
    fun list(): TopicListResource = TopicListResource(topicService.list().map(::TopicResource))

    @RestGetMapping(value = ["{name}"])
    fun get(@PathVariable name: String): TopicDetailResource = TopicDetailResource(topicService.get(name))
}