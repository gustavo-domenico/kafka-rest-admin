package kafka.rest.admin.rest.controllers.advice

import kafka.rest.admin.exceptions.ClusterConnectionException
import org.springframework.hateoas.mediatype.vnderrors.VndErrors.VndError
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalControllerAdvice {
    val answerToTheUniverse = "42" // We don't have logging yet so, obviously, no log refs are available

    @ResponseBody
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ClusterConnectionException::class)
    fun handleClusterConnectionException(e: ClusterConnectionException): ResponseEntity<VndError> {
        return ResponseEntity(VndError(answerToTheUniverse, e.message!!), INTERNAL_SERVER_ERROR)
    }
}