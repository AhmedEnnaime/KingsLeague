package com.youcode.kingsleague.match_service.controllers

import GlobalController
import com.youcode.kingsleague.match_service.models.dto.StadiumDTO
import com.youcode.kingsleague.match_service.services.StadiumService
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/stadiums", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class StadiumController(private  val stadiumService: StadiumService): GlobalController<StadiumDTO, Long>() {
}