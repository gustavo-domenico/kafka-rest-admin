package kafka.rest.admin.infrastructure.configuration

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes
import org.springframework.stereotype.Component
import org.springframework.web.context.request.WebRequest

@Component
class ErrorAttributesConfiguration : DefaultErrorAttributes() {
    override fun getErrorAttributes(webRequest: WebRequest, includeStackTrace: Boolean): Map<String, Any> {
        return super.getErrorAttributes(webRequest, includeStackTrace);
    }
}