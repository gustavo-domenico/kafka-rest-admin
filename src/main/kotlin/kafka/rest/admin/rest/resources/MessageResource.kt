package kafka.rest.admin.rest.resources

import com.fasterxml.jackson.annotation.JsonUnwrapped
import kafka.rest.admin.domain.models.Message
import org.springframework.hateoas.RepresentationModel

open class MessageResource(message: Message) : RepresentationModel<MessageResource>() {
    @JsonUnwrapped
    val content: Message = message

    init {
    }
}