package com.youcode.kingsleague.tournamentservice.controllers

import com.youcode.kingsleague.tournamentservice.models.dto.CupDTO
import com.youcode.kingsleague.tournamentservice.services.TournamentService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/cups", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class CupController(private val tournamentService: TournamentService) {

    @GetMapping("/{id}")
    fun findCupById(@PathVariable id: Long): ResponseEntity<CupDTO?> {
        val cup: CupDTO = tournamentService.findCupById(id)
        return ResponseEntity(cup, HttpStatus.OK)
    }
}