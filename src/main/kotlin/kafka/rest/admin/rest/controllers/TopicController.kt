package kafka.rest.admin.rest.controllers

import kafka.rest.admin.domain.services.TopicService
import kafka.rest.admin.infrastructure.annotations.RestGetMapping
import kafka.rest.admin.infrastructure.annotations.RestPostMapping
import kafka.rest.admin.rest.requests.TopicRequest
import kafka.rest.admin.rest.resources.TopicDetailResource
import kafka.rest.admin.rest.resources.TopicListResource
import kafka.rest.admin.rest.resources.TopicResource
import org.apache.kafka.clients.admin.Config
import org.apache.kafka.clients.admin.DescribeConfigsResult
import org.apache.kafka.common.Metric
import org.apache.kafka.common.MetricName
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/topics")
class TopicController(val topicService: TopicService) {
    @RestGetMapping
    fun list(): TopicListResource = TopicListResource(topicService.list().map(::TopicResource))

    @RestGetMapping(value = ["{name}"])
    fun get(@PathVariable name: String): TopicDetailResource = TopicDetailResource(topicService.get(name))

    @RestPostMapping
    fun add(@RequestBody request: TopicRequest): TopicDetailResource = TopicDetailResource(topicService.add(request.name, request.partitions))

    @DeleteMapping(value = ["/{name}"])
    @ResponseStatus(NO_CONTENT)
    fun remove(@PathVariable name: String): Unit = topicService.remove(name)
}