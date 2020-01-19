package kafka.rest.admin.controllers

import kafka.rest.admin.domain.services.TopicService
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/topics")
class TopicController(val topicService: TopicService) {
    @GetMapping
    fun list(): Set<String> = topicService.list()

    @GetMapping(value = ["/{name}"], produces = [APPLICATION_JSON_VALUE])
    @ResponseBody
    fun get(@PathVariable("name") name: String?): List<String> = topicService.get(name)
}