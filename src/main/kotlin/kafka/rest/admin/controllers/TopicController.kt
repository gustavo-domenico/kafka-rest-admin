package kafka.rest.admin.controllers

import kafka.rest.admin.domain.KafkaConsumerFactory
import kafka.rest.admin.domain.TopicService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/topics")
class TopicController {
    @Autowired
    private val topicService: TopicService? = null

    @GetMapping
    fun list(@ModelAttribute kafkaConsumerFactory: KafkaConsumerFactory?): Set<String> {
        return topicService!!.list(kafkaConsumerFactory)
    }

    @GetMapping(value = ["{name}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    operator fun get(@ModelAttribute kafkaConsumerFactory: KafkaConsumerFactory?,
                     @PathVariable("name") name: String?): List<String> {
        return topicService!![kafkaConsumerFactory, name]
    }
}