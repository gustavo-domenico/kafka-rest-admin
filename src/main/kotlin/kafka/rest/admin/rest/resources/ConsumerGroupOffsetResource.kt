package kafka.rest.admin.rest.resources

import com.fasterxml.jackson.annotation.JsonUnwrapped
import kafka.rest.admin.domain.models.ConsumerGroupOffset
import kafka.rest.admin.rest.controllers.TopicController
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder

open class ConsumerGroupOffsetResource(consumerGroupOffset: ConsumerGroupOffset) : RepresentationModel<ConsumerGroupOffsetResource>() {
    @JsonUnwrapped
    val content: ConsumerGroupOffset = consumerGroupOffset

    init {
        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TopicController::class.java).get(consumerGroupOffset.topicName)).withRel("topic"))
    }
}