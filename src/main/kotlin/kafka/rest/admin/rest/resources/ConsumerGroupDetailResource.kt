package kafka.rest.admin.rest.resources

import com.fasterxml.jackson.annotation.JsonUnwrapped
import kafka.rest.admin.domain.models.ConsumerGroupDetail
import kafka.rest.admin.rest.controllers.ConsumerGroupController
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

open class ConsumerGroupDetailResource(consumerGroupDetail: ConsumerGroupDetail) : RepresentationModel<ConsumerGroupDetailResource>() {
    @JsonUnwrapped
    val content: ConsumerGroupDetail = consumerGroupDetail

    init {
        add(linkTo(methodOn(ConsumerGroupController::class.java).get(consumerGroupDetail.consumerGroup.id)).withSelfRel())
        add(linkTo(methodOn(ConsumerGroupController::class.java).offsets(consumerGroupDetail.consumerGroup.id)).withRel("offsets"))
        add(linkTo(methodOn(ConsumerGroupController::class.java).list()).withRel("consumer-groups"))
    }
}