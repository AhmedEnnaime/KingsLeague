package com.youcode.kingsleague.matchday_service.services.client

import com.youcode.kingsleague.matchday_service.models.transients.Match
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "MATCH-SERVICE")
interface MatchServiceClient {
    @GetMapping( "/api/v1/matches/matchDay/{matchDayId}")
    fun findMatchesByMatchDayId(@PathVariable matchDayId: Long): List<Match>
}