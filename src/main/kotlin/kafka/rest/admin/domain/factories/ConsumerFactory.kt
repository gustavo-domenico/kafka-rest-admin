package kafka.rest.admin.domain.factories

import kafka.rest.admin.infrastructure.annotations.Factory
import org.apache.kafka.clients.consumer.Consumer
import org.springframework.kafka.core.ConsumerFactory

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Factory
class ConsumerFactory (val consumerFactory: ConsumerFactory<Any, Any>) {
    fun build(): Consumer<Any, Any> = consumerFactory.createConsumer()
}