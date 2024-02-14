package com.youcode.kingsleague.tournamentservice.controllers

import GlobalController
import com.youcode.kingsleague.tournamentservice.models.dto.LeagueDTO
import com.youcode.kingsleague.tournamentservice.services.LeagueService
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/leagues", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class LeagueController(private val leagueService: LeagueService): GlobalController<LeagueDTO, Long>() {
}