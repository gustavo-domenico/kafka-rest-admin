package kafka.rest.admin.rest.controllers.advice

import kafka.rest.admin.exceptions.ClusterConnectionException
import kafka.rest.admin.exceptions.EntityNotFoundException
import org.springframework.hateoas.mediatype.vnderrors.VndErrors.VndError
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@ControllerAdvice
class GlobalControllerAdvice {
    @ResponseBody
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ClusterConnectionException::class)
    fun handleClusterConnectionException(e: ClusterConnectionException): VndError {
        return VndError(e.javaClass.simpleName, e.message!!)
    }

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(e: EntityNotFoundException): VndError {
        return VndError(e.javaClass.simpleName, e.message!!)
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatchException(e: MethodArgumentTypeMismatchException): VndError {
        return VndError(e.javaClass.simpleName, e.message!!)
    }
}