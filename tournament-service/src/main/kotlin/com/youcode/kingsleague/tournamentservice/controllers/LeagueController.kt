package com.youcode.kingsleague.tournamentservice.controllers

import com.youcode.kingsleague.tournamentservice.models.dto.LeagueDTO
import com.youcode.kingsleague.tournamentservice.services.TournamentService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/leagues", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class LeagueController(private val tournamentService: TournamentService) {

    @GetMapping("/{id}")
    fun findLeagueById(@PathVariable id: Long): ResponseEntity<LeagueDTO?> {
        val league: LeagueDTO = tournamentService.findLeagueById(id)
        return ResponseEntity(league, HttpStatus.OK)
    }
}