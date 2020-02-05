package kafka.rest.admin.domain.models

data class Message(val key: String?, val content: String, val timestamp: Long) {
    constructor(key: String, content: String): this(key, content, -1)
}