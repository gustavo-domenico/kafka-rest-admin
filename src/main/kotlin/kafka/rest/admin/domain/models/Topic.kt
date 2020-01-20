package kafka.rest.admin.domain.models

import org.apache.kafka.clients.admin.NewTopic

data class Topic(val name: String) {
    fun toNewTopic() = NewTopic(name, 1, 1)
}