package kafka.rest.admin.domain.models

data class Message(val key: String?, val content: String, val offset: Long, val timestamp: Long) {
    constructor(key: String, content: String, offset: Long): this(key, content, offset,-1)
}