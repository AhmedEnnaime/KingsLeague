package com.youcode.kingsleague.tournamentservice.controllers

import GlobalController
import com.youcode.kingsleague.tournamentservice.models.dto.CupDTO
import com.youcode.kingsleague.tournamentservice.services.CupService
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/cups", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class CupController(private val cupService: CupService): GlobalController<CupDTO, Long>() {
}