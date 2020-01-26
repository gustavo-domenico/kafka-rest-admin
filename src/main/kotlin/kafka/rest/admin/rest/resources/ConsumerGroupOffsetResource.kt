package kafka.rest.admin.rest.resources

import com.fasterxml.jackson.annotation.JsonUnwrapped
import kafka.rest.admin.domain.models.ConsumerGroupOffset
import org.springframework.hateoas.RepresentationModel

open class ConsumerGroupOffsetResource(consumerGroupOffset: ConsumerGroupOffset) : RepresentationModel<ConsumerGroupOffsetResource>() {
    @JsonUnwrapped
    val content: ConsumerGroupOffset = consumerGroupOffset

    init {
    }
}