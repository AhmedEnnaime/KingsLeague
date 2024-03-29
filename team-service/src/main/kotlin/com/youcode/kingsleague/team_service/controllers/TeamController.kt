package com.youcode.kingsleague.team_service.controllers

import GlobalController
import com.youcode.kingsleague.team_service.models.dto.TeamDTO
import com.youcode.kingsleague.team_service.models.entities.Team
import com.youcode.kingsleague.team_service.services.TeamService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/v1/teams", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class TeamController(private val teamService: TeamService): GlobalController<TeamDTO, Long>() {
}