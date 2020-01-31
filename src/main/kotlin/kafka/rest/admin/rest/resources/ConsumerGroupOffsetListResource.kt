package kafka.rest.admin.rest.resources

import kafka.rest.admin.rest.controllers.ConsumerGroupController
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

open class ConsumerGroupOffsetListResource(id: String, resources: List<ConsumerGroupOffsetResource>) : CollectionModel<ConsumerGroupOffsetResource>(resources) {
    init {
        add(linkTo(methodOn(ConsumerGroupController::class.java).offsets(id)).withSelfRel())
    }
}