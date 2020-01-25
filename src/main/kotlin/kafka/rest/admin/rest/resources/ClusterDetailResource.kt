package kafka.rest.admin.rest.resources

import com.fasterxml.jackson.annotation.JsonUnwrapped
import kafka.rest.admin.domain.models.ClusterDetail
import kafka.rest.admin.rest.controllers.ClusterController
import kafka.rest.admin.rest.controllers.ConsumerGroupController
import kafka.rest.admin.rest.controllers.TopicController
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

open class ClusterDetailResource(clusterDetail: ClusterDetail) : RepresentationModel<ClusterDetailResource>() {
    @JsonUnwrapped
    val content: ClusterDetail = clusterDetail

    init {
        add(linkTo(methodOn(ClusterController::class.java).get()).withSelfRel())
        add(linkTo(methodOn(TopicController::class.java).list()).withRel("topics"))
        add(linkTo(methodOn(ConsumerGroupController::class.java).list()).withRel("consumer-groups"))
    }
}