package com.youcode.kingsleague.tournamentteamsservice.services.client

import com.youcode.kingsleague.tournamentteamsservice.models.transients.Team
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "TEAM-SERVICE")
interface TeamServiceClient {

    @GetMapping("/api/v1/teams/{teamId}")
    fun findTeamById(@PathVariable teamId: Long): Team
}