package kafka.rest.admin.domain.models

import org.apache.kafka.clients.admin.NewTopic

data class Topic(val name: String, val isInternal: Boolean = false) {
    fun toNewTopic() = NewTopic(name, 1, 1)
}