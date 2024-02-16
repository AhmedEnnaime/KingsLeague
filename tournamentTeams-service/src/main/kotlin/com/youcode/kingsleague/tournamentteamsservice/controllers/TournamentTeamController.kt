package com.youcode.kingsleague.tournamentteamsservice.controllers

import com.youcode.kingsleague.tournamentteamsservice.services.TournamentTeamService
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/tournamentTeams", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class TournamentTeamController(private val tournamentTeamService: TournamentTeamService) {
}