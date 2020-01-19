package kafka.rest.admin.domain.factories

import org.apache.kafka.clients.consumer.Consumer
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.stereotype.Service

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Service
class ConsumerFactory (val consumerFactory: ConsumerFactory<Any, Any>) {
    fun build(): Consumer<Any, Any> = consumerFactory.createConsumer()
}