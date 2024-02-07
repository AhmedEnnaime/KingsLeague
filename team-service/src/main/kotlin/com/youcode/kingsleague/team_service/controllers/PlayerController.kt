package com.youcode.kingsleague.team_service.controllers

import GlobalController
import com.youcode.kingsleague.team_service.models.dto.PlayerDTO
import com.youcode.kingsleague.team_service.services.PlayerService
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/players", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class PlayerController(private val playerService: PlayerService): GlobalController<PlayerDTO, Long>() {
}