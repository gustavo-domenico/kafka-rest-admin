package kafka.rest.admin.exceptions

class EntityNotFoundException(name: String, cause: Throwable) : RuntimeException(String.format(DEFAULT_MESSAGE, name), cause) {
    companion object {
        private const val DEFAULT_MESSAGE = "Could not find the requested entity [%s]."
    }
}