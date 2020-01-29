package kafka.rest.admin.rest.controllers

import kafka.rest.admin.domain.services.MessageService
import kafka.rest.admin.infrastructure.annotations.RestGetMapping
import kafka.rest.admin.infrastructure.routes.Routes.Companion.MESSAGES
import kafka.rest.admin.rest.resources.MessageResource
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(MESSAGES)
class MessageController(val messageService: MessageService) {
    @RestGetMapping(value = ["/topic/{topic}/partition/{partition}/from/{offset}"])
    fun from(@PathVariable("topic") topic: String?,
             @PathVariable("partition") partition: Int?,
             @PathVariable("offset") offset: Long?
    ): List<MessageResource> = messageService.from(topic, partition, offset).map(::MessageResource)
}