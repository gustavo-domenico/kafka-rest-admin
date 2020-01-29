package kafka.rest.admin.rest.resources

import kafka.rest.admin.domain.models.ConsumerGroup
import kafka.rest.admin.rest.controllers.ConsumerGroupController
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

class ConsumerGroupResource(consumerGroup: ConsumerGroup) : EntityModel<ConsumerGroup>(consumerGroup) {
    init {
        add(linkTo(methodOn(ConsumerGroupController::class.java).get(consumerGroup.id)).withSelfRel())
    }
}