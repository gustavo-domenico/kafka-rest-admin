package kafka.rest.admin.rest.resources

import com.fasterxml.jackson.annotation.JsonUnwrapped
import kafka.rest.admin.domain.models.ConsumerGroup
import org.springframework.hateoas.RepresentationModel


class ConsumerGroupResource(consumerGroup: ConsumerGroup) : RepresentationModel<ConsumerGroupResource>() {
    @JsonUnwrapped
    val content: ConsumerGroup = consumerGroup

    init {
//        add(linkTo(methodOn(TopicController::class.java).get(topic.name)).withSelfRel())
    }
}