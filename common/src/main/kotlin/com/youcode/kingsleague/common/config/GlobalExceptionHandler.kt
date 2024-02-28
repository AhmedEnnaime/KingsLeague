package com.youcode.kingsleague.common.config

import com.youcode.kingsleague.common.exceptions.MatchDayAlreadyExistsException
import com.youcode.kingsleague.common.exceptions.RefereeAlreadyAssignedInMatch
import com.youcode.kingsleague.common.exceptions.ResourceNotFoundException
import com.youcode.kingsleague.common.models.ErrorMessage
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.IllegalStateException

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler
    fun handleIllegalStateException(ex: IllegalStateException): ResponseEntity<ErrorMessage>
    {
        val errorMessage = ErrorMessage(
            HttpStatus.NOT_FOUND.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleResourceNotFoundException(ex: ResourceNotFoundException): ResponseEntity<ErrorMessage>
    {
        val errorMessage = ErrorMessage(
            HttpStatus.NOT_FOUND.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(MatchDayAlreadyExistsException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMatchDayAlreadyExistsExistsException(ex: MatchDayAlreadyExistsException): ResponseEntity<ErrorMessage>
    {
        val errorMessage = ErrorMessage(
            HttpStatus.BAD_REQUEST.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(RefereeAlreadyAssignedInMatch::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleRefereeAlreadyAssignedException(ex: RefereeAlreadyAssignedInMatch): ResponseEntity<ErrorMessage>
    {
        val errorMessage = ErrorMessage(
            HttpStatus.BAD_REQUEST.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }
}