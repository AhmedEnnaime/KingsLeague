package com.youcode.kingsleague.match_service.controllers

import com.youcode.kingsleague.match_service.models.dto.MatchRefereeDTO
import com.youcode.kingsleague.match_service.services.MatchRefereeService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/matchReferees", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class MatchRefereeController(private val matchRefereeService: MatchRefereeService) {

    @PostMapping
    fun assignRefereeToMatch(@RequestBody @Valid matchRefereeDTO: MatchRefereeDTO): ResponseEntity<MatchRefereeDTO> {
        val savedMatchReferee: MatchRefereeDTO = matchRefereeService.assignRefereeToMatch(matchRefereeDTO)
        return ResponseEntity(savedMatchReferee, HttpStatus.CREATED)
    }
}