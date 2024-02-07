package com.youcode.kingsleague.team_service.controllers

import GlobalController
import com.youcode.kingsleague.team_service.models.dto.TeamDTO
import com.youcode.kingsleague.team_service.services.TeamService
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/teams", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class TeamController(private val teamService: TeamService): GlobalController<TeamDTO, Long>() {
}