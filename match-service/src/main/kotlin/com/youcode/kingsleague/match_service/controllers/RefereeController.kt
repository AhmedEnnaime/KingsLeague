package com.youcode.kingsleague.match_service.controllers

import GlobalController
import com.youcode.kingsleague.match_service.models.dto.RefereeDTO
import com.youcode.kingsleague.match_service.services.RefereeService
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/referees", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class RefereeController(private val refereeService: RefereeService): GlobalController<RefereeDTO, Long>() {
}