package com.youcode.kingsleague.match_service.controllers

import GlobalController
import com.youcode.kingsleague.match_service.models.dto.ResultDTO
import com.youcode.kingsleague.match_service.services.ResultService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/results", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class ResultController(private val resultService: ResultService): GlobalController<ResultDTO, Long>() {

    @GetMapping("/match/{matchId}")
    fun findResultsByMatchId(@PathVariable matchId: Long): ResponseEntity<ResultDTO> {
        val result: ResultDTO = resultService.findResultByMatchId(matchId)
        return ResponseEntity(result, HttpStatus.OK)
    }
}