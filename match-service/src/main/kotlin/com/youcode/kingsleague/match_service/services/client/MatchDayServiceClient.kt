package com.youcode.kingsleague.match_service.services.client

import com.youcode.kingsleague.match_service.models.transients.MatchDay
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "MATCHDAY-SERVICE")
interface MatchDayServiceClient {

    @GetMapping("/api/v1/matchDays/{matchDayId}")
    fun findMatchDayById(@PathVariable matchDayId: Long): MatchDay
}