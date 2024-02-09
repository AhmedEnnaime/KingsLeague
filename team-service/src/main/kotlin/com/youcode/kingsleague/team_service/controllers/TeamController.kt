package com.youcode.kingsleague.team_service.controllers

import com.youcode.kingsleague.team_service.models.dto.TeamDTO
import com.youcode.kingsleague.team_service.models.entities.Team
import com.youcode.kingsleague.team_service.services.TeamService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/v1/teams", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class TeamController(private val teamService: TeamService) {

    @PostMapping
    fun createTeam(@Valid @RequestBody team: TeamDTO): ResponseEntity<TeamDTO> {
        val createdTeam = teamService.save(team)
        return ResponseEntity(createdTeam, HttpStatus.CREATED)
    }

    @GetMapping
    fun getAllTeams(): ResponseEntity<List<TeamDTO?>> {
        val teams = teamService.getAll()
        return ResponseEntity(teams, HttpStatus.OK)
    }

    @GetMapping("/{teamId}")
    fun findTeamByID(@PathVariable("teamId") teamId: Long): ResponseEntity<TeamDTO?> {
        val team = teamService.findByID(teamId)
        return ResponseEntity(team, HttpStatus.OK)
    }

    @PutMapping("/{teamId}")
    fun updateTeam(@PathVariable("teamId") teamId: Long, @Valid @RequestBody teamDTO: TeamDTO): ResponseEntity<TeamDTO> {
        val updatedTeam = teamService.update(teamId, teamDTO)
        return ResponseEntity(updatedTeam, HttpStatus.OK)
    }

    @DeleteMapping("/{teamId}")
    fun deleteTeam(@PathVariable("teamId") teamId: Long): ResponseEntity<Map<String, String>> {
        teamService.delete(teamId)
        val response: MutableMap<String, String> = HashMap()
        response["message"] = "Team deleted successfully."
        response["deletedElementIdentifier"] = teamId.toString()
        return ResponseEntity<Map<String, String>>(response, HttpStatus.OK)
    }
}