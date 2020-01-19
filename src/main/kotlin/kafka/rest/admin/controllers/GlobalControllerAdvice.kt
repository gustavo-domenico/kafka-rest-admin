package kafka.rest.admin.controllers

import kafka.rest.admin.domain.KafkaConsumerFactory
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestParam

@ControllerAdvice
class GlobalControllerAdvice {
    @ModelAttribute
    fun inject(@RequestParam("bootstrap-servers") bootstrapServers: String?, model: Model) {
        model.addAttribute("kafkaConsumerFactory", KafkaConsumerFactory.create(bootstrapServers))
    }
}