package com.youcode.kingsleague.match_service.services.client

import com.youcode.kingsleague.match_service.models.embaddables.TournamentTeamKey
import com.youcode.kingsleague.match_service.models.transients.TournamentTeam
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "TOURNAMENTTEAMS-SERVICE")
interface TournamentTeamServiceClient {
    @GetMapping("/api/v1/tournamentTeams/{tournamentTeamId}")
    fun findTournamentTeamById(@PathVariable tournamentTeamId: TournamentTeamKey): TournamentTeam
}