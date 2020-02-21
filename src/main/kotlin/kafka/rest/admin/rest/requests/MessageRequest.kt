package kafka.rest.admin.rest.requests

data class MessageRequest(val key: String?, val content: String)