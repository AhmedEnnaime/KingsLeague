package com.youcode.kingsleague.match_service.services.client

import com.youcode.kingsleague.match_service.models.transients.TournamentTeam
import jakarta.validation.Valid
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "TOURNAMENTTEAMS-SERVICE")
interface TournamentTeamServiceClient {
    @GetMapping("/api/v1/tournamentTeams/tournamentId/{tournamentId}/teamId/{teamId}")
    fun findTournamentTeamById(@PathVariable tournamentId: Long, @PathVariable teamId: Long): TournamentTeam
    @PostMapping("/api/v1/tournamentTeams/updatePoints")
    fun updateTeamTournamentPoints(@RequestBody @Valid tournamentTeam: TournamentTeam)
}