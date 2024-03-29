package com.youcode.kingsleague.team_service.controllers

import com.youcode.kingsleague.team_service.models.dto.PlayerDTO
import com.youcode.kingsleague.team_service.models.dto.TeamDTO
import com.youcode.kingsleague.team_service.models.dto.TeamPlayerDTO
import com.youcode.kingsleague.team_service.models.embeddables.TeamPlayerKey
import com.youcode.kingsleague.team_service.services.TeamPlayerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
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

    @GetMapping
    fun getAllTeamPlayers(): ResponseEntity<List<TeamPlayerDTO?>> {
        val teamPlayers = teamPlayerService.findAll()
        return ResponseEntity(teamPlayers, HttpStatus.OK)
    }

    @GetMapping("/players/{teamId}")
    fun getPlayersByTeam(@PathVariable("teamId") teamId: Long): ResponseEntity<List<PlayerDTO>> {
        val foundPlayers = teamPlayerService.findPlayersByTeam(teamId)
        return ResponseEntity(foundPlayers, HttpStatus.OK)
    }

    @GetMapping("/teams/{playerId}")
    fun getTeamsByPlayer(@PathVariable("playerId") playerId: Long): ResponseEntity<List<TeamDTO>> {
        val foundTeams = teamPlayerService.findTeamsByPlayer(playerId)
        return ResponseEntity(foundTeams, HttpStatus.OK)
    }

    @DeleteMapping("/team/{teamId}/player/{playerId}")
    fun removePlayerFromTeam(@PathVariable teamId: Long, @PathVariable playerId: Long): ResponseEntity<Map<String, String>> {
        teamPlayerService.removePlayerFromTeam(teamId, playerId)
        val response = mutableMapOf<String, String>()
        val teamPlayerKey = TeamPlayerKey(teamId, playerId)
        response["message"] = "Player removed from Team successfully."
        response["deletedElementIdentifier"] = teamPlayerKey.toString()
        return ResponseEntity(response, HttpStatus.OK)
    }

}