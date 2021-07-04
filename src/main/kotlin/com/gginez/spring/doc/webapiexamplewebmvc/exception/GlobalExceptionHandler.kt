package com.gginez.spring.doc.webapiexamplewebmvc.exception

import org.springframework.context.support.DefaultMessageSourceResolvable
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.validation.ConstraintViolationException

@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun handleConstraintViolationException(exception: ConstraintViolationException): ErrorResponse {
        return ErrorResponse(
                message = exception.constraintViolations.map { it.toString() }.joinToString(";"),
                status = HttpStatus.BAD_REQUEST.name
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val errorResponse = ErrorResponse(
                message = ex.bindingResult.allErrors.map { "${(it.arguments?.get(0) as DefaultMessageSourceResolvable).defaultMessage}: ${it.defaultMessage.toString()}" }.joinToString(";"),
                status = HttpStatus.BAD_REQUEST.name
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)

    }

//    @ResponseStatus(HttpStatus.CONFLICT)
//    @ExceptionHandler(value = [EntityAlreadyExistsException::class])
//    fun handleEntityAlreadyExists(ex: EntityAlreadyExistsException): ErrorResponse {
//        return ErrorResponse(
//                message = ex.message ?: "",
//                status = HttpStatus.CONFLICT.name
//        )
//    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = [Exception::class])
    fun handleException(ex: Exception): ErrorResponse {
        return ErrorResponse(
                message = ex.message ?: "",
                status = HttpStatus.INTERNAL_SERVER_ERROR.name
        )
    }

}