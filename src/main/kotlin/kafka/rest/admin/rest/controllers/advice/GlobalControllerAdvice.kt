package kafka.rest.admin.rest.controllers.advice

import kafka.rest.admin.exceptions.ClusterConnectionException
import kafka.rest.admin.exceptions.EntityNotFoundException
import org.springframework.hateoas.mediatype.vnderrors.VndErrors.VndError
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalControllerAdvice {
    @ResponseBody
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ClusterConnectionException::class)
    fun handleClusterConnectionException(e: ClusterConnectionException): ResponseEntity<VndError> {
        return ResponseEntity(VndError(e.javaClass.simpleName, e.message!!), INTERNAL_SERVER_ERROR)
    }

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(e: EntityNotFoundException): ResponseEntity<VndError> {
        return ResponseEntity(VndError(e.javaClass.simpleName, e.message!!), NOT_FOUND)
    }
}