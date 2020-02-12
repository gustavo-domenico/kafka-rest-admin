package kafka.rest.admin.infrastructure.configuration

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes
import org.springframework.stereotype.Component
import org.springframework.web.context.request.WebRequest
import java.util.*

@Component
class ErrorAttributesConfiguration : DefaultErrorAttributes() {
    override fun getErrorAttributes(webRequest: WebRequest, includeStackTrace: Boolean): Map<String, Any> {
        val errorAttributes: MutableMap<String, Any> = LinkedHashMap()
        errorAttributes["timestamp"] = Date()
        return errorAttributes
    }
}