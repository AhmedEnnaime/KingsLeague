package com.youcode.kingsleague.match_service.services.client

import com.youcode.kingsleague.match_service.models.transients.Round
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "ROUND-SERVICE")
interface RoundServiceClient {
    @GetMapping("/api/v1/rounds/{roundId}")
    fun findRoundById(@PathVariable roundId: Long): Round
}