package kafka.rest.admin.rest.resources

import kafka.rest.admin.domain.models.Topic
import kafka.rest.admin.rest.controllers.TopicController
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

class TopicResource(topic: Topic) : EntityModel<Topic>(topic) {
    init {
        add(linkTo(methodOn(TopicController::class.java).get(topic.name)).withSelfRel())
    }
}