package kafka.rest.admin.rest.resources

import kafka.rest.admin.domain.models.Message
import org.springframework.hateoas.EntityModel

open class MessageResource(message: Message) : EntityModel<Message>(message) {

}