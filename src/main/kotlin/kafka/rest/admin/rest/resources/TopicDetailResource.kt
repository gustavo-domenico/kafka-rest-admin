package kafka.rest.admin.rest.resources

import kafka.rest.admin.domain.models.TopicDetail
import kafka.rest.admin.rest.controllers.TopicController
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

open class TopicDetailResource(topicDetail: TopicDetail) : EntityModel<TopicDetail>(topicDetail) {
    init {
        add(linkTo(methodOn(TopicController::class.java).get(topicDetail.topic.name)).withSelfRel())
        add(linkTo(methodOn(TopicController::class.java).list()).withRel("topics"))
    }
}