package kafka.rest.admin.rest.resources

import kafka.rest.admin.rest.controllers.TopicController
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

open class TopicListResource(resources: List<TopicResource>) : CollectionModel<TopicResource>(resources) {
    init {
        add(linkTo(methodOn(TopicController::class.java).list()).withSelfRel())
    }
}