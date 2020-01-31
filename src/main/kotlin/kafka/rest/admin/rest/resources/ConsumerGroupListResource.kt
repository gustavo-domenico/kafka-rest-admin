package kafka.rest.admin.rest.resources

import kafka.rest.admin.rest.controllers.ConsumerGroupController
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

open class ConsumerGroupListResource(resources: List<ConsumerGroupResource>) : CollectionModel<ConsumerGroupResource>(resources) {
    init {
        add(linkTo(methodOn(ConsumerGroupController::class.java).list()).withSelfRel())
    }
}