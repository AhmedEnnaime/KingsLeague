package com.youcode.kingsleague.matchday_service.services.client

import com.youcode.kingsleague.matchday_service.models.transients.League
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "TOURNAMENT-SERVICE")
interface LeagueServiceClient {

    @GetMapping( "/api/v1/leagues/{leagueId}")
    fun findLeagueById(@PathVariable leagueId: Long): League
}