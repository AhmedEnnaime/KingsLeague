package com.youcode.kingsleague.tournamentteamsservice.controllers

import com.youcode.kingsleague.tournamentteamsservice.models.dto.TournamentTeamDTO
import com.youcode.kingsleague.tournamentteamsservice.models.embeddables.TournamentTeamKey
import com.youcode.kingsleague.tournamentteamsservice.models.transients.Team
import com.youcode.kingsleague.tournamentteamsservice.models.transients.Tournament
import com.youcode.kingsleague.tournamentteamsservice.services.TournamentTeamService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/tournamentTeams", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class TournamentTeamController(private val tournamentTeamService: TournamentTeamService) {

    @PostMapping
    fun registerTeam(@Valid @RequestBody tournamentTeamDTO: TournamentTeamDTO): ResponseEntity<TournamentTeamDTO> {
        val savedTournamentTeam = tournamentTeamService.registerTeamInTournament(tournamentTeamDTO)
        return ResponseEntity(savedTournamentTeam, HttpStatus.CREATED)
    }

    @DeleteMapping("/{teamId}/tournament/{tournamentId}")
    fun removeTeamFromTournament(@PathVariable teamId: Long, @PathVariable tournamentId: Long): ResponseEntity<Map<String, String>> {
        tournamentTeamService.removeTeamFromTournament(teamId, tournamentId)
        val response = mutableMapOf<String, String>()
        val tournamentTeamKey = TournamentTeamKey(teamId, tournamentId)
        response["message"] = "Team removed from Tournament successfully."
        response["deletedElementIdentifier"] = tournamentTeamKey.toString()
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/teams/{tournamentId}")
    fun getTournamentTeams(@PathVariable tournamentId: Long): ResponseEntity<List<Team>> {
        val teams: List<Team> = tournamentTeamService.findTournamentTeams(tournamentId)
        return ResponseEntity(teams, HttpStatus.OK)
    }

    @GetMapping("/tournaments/{teamId}")
    fun getTeamTournaments(@PathVariable teamId: Long): ResponseEntity<List<Tournament>> {
        val tournaments: List<Tournament> = tournamentTeamService.findTeamTournaments(teamId)
        return ResponseEntity(tournaments, HttpStatus.OK)
    }

    @GetMapping("/tournamentId/{tournamentId}/teamId/{teamId}")
    fun findTournamentTeamById(@PathVariable tournamentId: Long, @PathVariable teamId: Long): ResponseEntity<TournamentTeamDTO> {
        val tournamentTeam: TournamentTeamDTO = tournamentTeamService.findTournamentTeamById(tournamentId, teamId)
        return ResponseEntity(tournamentTeam, HttpStatus.OK)
    }

    @PatchMapping("/updatePoints")
    fun updateTeamTournamentPoints(@RequestBody @Valid tournamentTeam: TournamentTeamDTO): ResponseEntity<Map<String, String>> {
        tournamentTeamService.updateTeamPointsInTournament(tournamentTeam)
        val response = mutableMapOf<String, String>()
        response["message"] = "${tournamentTeam.points} Added."
        response["Team"] = tournamentTeam.team.toString()
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping
    fun getAllTournamentTeams(): ResponseEntity<List<TournamentTeamDTO>> {
        val tournamentTeams: List<TournamentTeamDTO> = tournamentTeamService.findAllTournamentTeams()
        return ResponseEntity(tournamentTeams, HttpStatus.OK)
    }
}