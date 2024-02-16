package com.youcode.kingsleague.tournamentteamsservice.services.client

import com.youcode.kingsleague.tournamentteamsservice.models.transients.Tournament
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "TOURNAMENT-SERVICE")
interface TournamentServiceClient {

    @GetMapping( "/api/v1/tournaments/{tournamentId}")
    fun findTournamentById(@PathVariable tournamentId: Long): Tournament

}