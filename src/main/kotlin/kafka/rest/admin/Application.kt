package kafka.rest.admin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.hateoas.config.EnableHypermediaSupport
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.HAL
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableHypermediaSupport(type = [HAL])
@EnableKafka
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}