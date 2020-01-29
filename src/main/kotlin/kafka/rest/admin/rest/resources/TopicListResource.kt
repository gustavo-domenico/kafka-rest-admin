package kafka.rest.admin.rest.resources

import kafka.rest.admin.rest.controllers.TopicController
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.server.core.Relation
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder

@Relation(collectionRelation = "topics")
open class TopicListResource(resources: List<TopicResource>) : CollectionModel<TopicResource>(resources) {
    init {
        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TopicController::class.java).list()).withSelfRel())
    }
}