package com.youcode.kingsleague.round_service.services.client

import com.youcode.kingsleague.round_service.models.transients.Cup
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "TOURNAMENT-SERVICE")
interface CupServiceClient {
    @GetMapping( "/api/v1/cups/{cupId}")
    fun findCupById(@PathVariable cupId: Long): Cup
}