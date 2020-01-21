package kafka.rest.admin.rest.resources

import com.fasterxml.jackson.annotation.JsonUnwrapped
import kafka.rest.admin.domain.models.TopicDetail
import kafka.rest.admin.rest.controllers.TopicController
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

open class TopicDetailResource(topicDetail: TopicDetail) : RepresentationModel<TopicDetailResource>() {
    @JsonUnwrapped
    val content: TopicDetail = topicDetail

    init {
        add(linkTo(methodOn(TopicController::class.java).get(topicDetail.topic.name)).withSelfRel())
    }
}