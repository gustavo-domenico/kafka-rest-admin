package kafka.rest.admin.controllers

import kafka.rest.admin.domain.KafkaConsumerFactory
import kafka.rest.admin.infrastructure.annotations.Advised
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestParam

@ControllerAdvice(annotations = [Advised::class])
class GlobalControllerAdvice {
    @ModelAttribute
    fun inject(@RequestParam("bootstrap-servers") bootstrapServers: String, model: Model) {
        model.addAttribute("kafkaConsumerFactory", KafkaConsumerFactory.create(bootstrapServers))
    }
}