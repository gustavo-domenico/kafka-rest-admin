package kafka.rest.admin.controllers

import kafka.rest.admin.domain.KafkaConsumerFactory
import kafka.rest.admin.domain.TopicService
import kafka.rest.admin.infrastructure.annotations.Advised
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*

@Advised
@RestController
@RequestMapping("/topics")
class TopicController (val topicService: TopicService){
    @GetMapping
    fun list(@ModelAttribute kafkaConsumerFactory: KafkaConsumerFactory): Set<String> =
            topicService.list(kafkaConsumerFactory)

    @GetMapping(value = ["{name}"], produces = [APPLICATION_JSON_VALUE])
    @ResponseBody
    fun get(@ModelAttribute kafkaConsumerFactory: KafkaConsumerFactory, @PathVariable("name") name: String?): List<String> =
            topicService.get(kafkaConsumerFactory, name)
}