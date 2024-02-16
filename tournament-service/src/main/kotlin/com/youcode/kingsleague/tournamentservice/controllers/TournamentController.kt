package com.youcode.kingsleague.tournamentservice.controllers

import com.youcode.kingsleague.tournamentservice.models.dto.CupDTO
import com.youcode.kingsleague.tournamentservice.models.dto.LeagueDTO
import com.youcode.kingsleague.tournamentservice.models.dto.TournamentDTO
import com.youcode.kingsleague.tournamentservice.services.TournamentService
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
@RequestMapping("api/v1/tournaments", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class TournamentController(private val tournamentService: TournamentService) {

    @PostMapping("/leagues")
    fun createLeague(@RequestBody @Valid leagueDTO: LeagueDTO): ResponseEntity<LeagueDTO> {
        val savedLeague: LeagueDTO = tournamentService.createLeague(leagueDTO)
        return ResponseEntity(savedLeague, HttpStatus.CREATED)
    }
    @PostMapping("/cups")
    fun createCup(@RequestBody @Valid cupDTO: CupDTO): ResponseEntity<CupDTO> {
        val savedCup: CupDTO = tournamentService.createCup(cupDTO)
        return ResponseEntity(savedCup, HttpStatus.CREATED)
    }

    @GetMapping
    fun findAllTournaments(): ResponseEntity<List<TournamentDTO>> {
        val tournaments: List<TournamentDTO> = tournamentService.findAll()
        return ResponseEntity(tournaments, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun findTournamentById(@PathVariable id: Long): ResponseEntity<TournamentDTO> {
        val tournament: TournamentDTO = tournamentService.findById(id)
        return ResponseEntity(tournament, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteTournament(@PathVariable id: Long): ResponseEntity<Map<String, String>> {
        tournamentService.delete(id)
        val response = mutableMapOf<String, String>()
        response["message"] = "Level deleted successfully."
        response["deletedElementIdentifier"] = id.toString()
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateTournament(@PathVariable id: Long, @RequestBody @Valid tournamentDTO: TournamentDTO): ResponseEntity<TournamentDTO> {
        val updatedTournament: TournamentDTO = tournamentService.update(id, tournamentDTO)
        return ResponseEntity(updatedTournament, HttpStatus.OK)
    }

}