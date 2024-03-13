package com.youcode.kingsleague.round_service.controllers

import GlobalController
import com.youcode.kingsleague.round_service.models.dto.RoundDTO
import com.youcode.kingsleague.round_service.services.RoundService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/rounds", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class RoundController(private val roundService: RoundService): GlobalController<RoundDTO, Long>() {

    @GetMapping("/tournament/{tournamentId}")
    fun getRoundsByTournamentId(@PathVariable tournamentId: Long): ResponseEntity<List<RoundDTO>> {
        val rounds: List<RoundDTO> = roundService.findRoundsByTournamentId(tournamentId)
        return ResponseEntity(rounds, HttpStatus.OK)
    }
}