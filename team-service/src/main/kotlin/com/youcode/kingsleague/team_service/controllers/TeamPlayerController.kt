package com.youcode.kingsleague.team_service.controllers

import com.youcode.kingsleague.team_service.models.dto.TeamPlayerDTO
import com.youcode.kingsleague.team_service.services.TeamPlayerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/teamPlayer")
@Validated
class TeamPlayerController(private val teamPlayerService: TeamPlayerService) {

    @PostMapping
    fun addPlayerToTeam(@Valid @RequestBody teamPlayerDTO: TeamPlayerDTO): ResponseEntity<TeamPlayerDTO> {
        val savedTeamPlayer = teamPlayerService.save(teamPlayerDTO)
        return ResponseEntity(savedTeamPlayer, HttpStatus.CREATED)
    }

    @GetMapping("/players/{teamId}")
    fun getPlayersByTeam(@PathVariable("teamId") teamId: Long): ResponseEntity<List<TeamPlayerDTO>> {
        val foundPlayers = teamPlayerService.findPlayersByTeam(teamId)
        return ResponseEntity(foundPlayers, HttpStatus.OK)
    }

    @GetMapping("/teams/{playerId}")
    fun getTeamsByPlayer(@PathVariable("playerId") playerId: Long): ResponseEntity<List<TeamPlayerDTO>> {
        val foundTeams = teamPlayerService.findTeamsByPlayer(playerId)
        return ResponseEntity(foundTeams, HttpStatus.OK)
    }

}