package kafka.rest.admin.rest.resources

import kafka.rest.admin.domain.models.Message
import kafka.rest.admin.rest.controllers.MessageController
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder

open class MessageResource(topic: String, partition: Int, offset: Long, message: Message) : EntityModel<Message>(message) {
    init {
        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MessageController::class.java)
                .raw(topic, partition, offset)).withRel("raw"))
    }
}