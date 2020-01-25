package kafka.rest.admin.rest.resources

import com.fasterxml.jackson.annotation.JsonUnwrapped
import kafka.rest.admin.domain.models.Topic
import kafka.rest.admin.rest.controllers.TopicController
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

class TopicResource(topic: Topic) : RepresentationModel<TopicResource>() {
    @JsonUnwrapped
    val content: Topic = topic

    init {
        add(linkTo(methodOn(TopicController::class.java).get(topic.name)).withSelfRel())
    }
}