package kafka.rest.admin.rest.resources

import kafka.rest.admin.domain.models.ConsumerGroupOffset
import kafka.rest.admin.rest.controllers.TopicController
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder

open class ConsumerGroupOffsetResource(consumerGroupOffset: ConsumerGroupOffset) : EntityModel<ConsumerGroupOffset>(consumerGroupOffset) {
    init {
        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TopicController::class.java).get(consumerGroupOffset.topicName)).withRel("topic"))
    }
}