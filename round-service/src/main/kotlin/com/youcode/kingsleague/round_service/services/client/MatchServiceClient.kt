package com.youcode.kingsleague.round_service.services.client

import com.youcode.kingsleague.round_service.models.transients.Match
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "MATCH-SERVICE")
interface MatchServiceClient {
    @GetMapping( "/api/v1/matches/round/{roundId}")
    fun findMatchesByRoundId(@PathVariable roundId: Long): List<Match>
}