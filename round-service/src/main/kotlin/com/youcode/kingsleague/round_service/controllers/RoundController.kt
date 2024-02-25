package com.youcode.kingsleague.round_service.controllers

import GlobalController
import com.youcode.kingsleague.round_service.models.dto.RoundDTO
import com.youcode.kingsleague.round_service.services.RoundService
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/rounds", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class RoundController(private val roundService: RoundService): GlobalController<RoundDTO, Long>() {
}