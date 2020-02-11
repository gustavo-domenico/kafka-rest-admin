package kafka.rest.admin.infrastructure.helpers

import kafka.rest.admin.exceptions.EntityNotFoundException
import org.apache.kafka.common.errors.UnknownTopicOrPartitionException

inline fun <T, R> T.runForEntity(
        entity: String,
        block: T.() -> R): R {
    return try {
        block()
    } catch (e: Throwable) {
        if (e.cause != null && e.cause is UnknownTopicOrPartitionException) {
            throw EntityNotFoundException(entity, e)
        }
        throw e
    }
}