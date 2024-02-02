package com.youcode.kingsleague.tournament_service.models.transients

import com.youcode.kingsleague.tournament_service.models.dto.LeagueDTO
import lombok.Builder
import java.time.LocalDate

@Builder
data class MatchDay (
    var id: Long,
    var date: LocalDate,
    var league: LeagueDTO,
)