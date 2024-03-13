package com.youcode.kingsleague.matchday_service.controllers

import GlobalController
import com.youcode.kingsleague.matchday_service.models.dto.MatchDayDTO
import com.youcode.kingsleague.matchday_service.services.MatchDayService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/matchDays", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class MatchDayController(private val matchDayService: MatchDayService): GlobalController<MatchDayDTO, Long>() {

    @GetMapping("/tournament/{tournamentId}")
    fun getMatchDaysByTournamentId(@PathVariable tournamentId: Long): ResponseEntity<List<MatchDayDTO>> {
        val matchDays: List<MatchDayDTO> = matchDayService.findByTournamentId(tournamentId)
        return ResponseEntity(matchDays, HttpStatus.OK)
    }
}