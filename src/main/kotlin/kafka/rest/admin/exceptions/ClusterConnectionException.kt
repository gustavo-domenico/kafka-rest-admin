package kafka.rest.admin.exceptions

class ClusterConnectionException(internalMessage: String) : RuntimeException(String.format(DEFAULT_MESSAGE, internalMessage)) {
    companion object {
        private const val DEFAULT_MESSAGE = "Could not connect to the kafka cluster. Message: %s."
    }
}