package com.youcode.kingsleague.match_service.controllers

import GlobalController
import com.youcode.kingsleague.match_service.models.dto.MatchDTO
import com.youcode.kingsleague.match_service.models.dto.RetrievalMatchDTO
import com.youcode.kingsleague.match_service.services.MatchService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/matches", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class MatchController(private val matchService: MatchService) {

    @GetMapping
    fun getAllMatches(): ResponseEntity<List<RetrievalMatchDTO?>>? {
        val matches: List<RetrievalMatchDTO?>? = matchService.findAll()
        return ResponseEntity(matches, HttpStatus.OK)
    }

    @PostMapping
    fun createMatch(@RequestBody @Valid match: MatchDTO): ResponseEntity<MatchDTO> {
        val savedMatch: MatchDTO = matchService.save(match)
        return ResponseEntity(savedMatch, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getMatchById(@PathVariable id: Long): ResponseEntity<RetrievalMatchDTO> {
        val foundMatch: RetrievalMatchDTO? = matchService.findByID(id)
        return ResponseEntity(foundMatch, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateMatch(@RequestBody @Valid match: MatchDTO, @PathVariable id: Long): ResponseEntity<MatchDTO> {
        val updatedMatch: MatchDTO = matchService.update(id, match)
        return ResponseEntity(updatedMatch, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteMatch(@PathVariable id: Long): ResponseEntity<Map<String, String>> {
        matchService.delete(id)
        val response = mutableMapOf<String, String>()
        response["message"] = "Match deleted successfully."
        response["deletedElementIdentifier"] = id.toString()
        return ResponseEntity(response, HttpStatus.OK)
    }
}