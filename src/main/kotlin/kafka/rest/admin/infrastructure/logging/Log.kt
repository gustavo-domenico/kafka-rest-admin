package kafka.rest.admin.infrastructure.logging

import org.slf4j.LoggerFactory

interface Log {
    fun logger() = LoggerFactory.getLogger(this.javaClass)
}