package com.youcode.kingsleague.match_service.models.transients

import lombok.Builder
import java.time.LocalDate

@Builder
data class MatchDay (
    var id: Long,
    var date: LocalDate,
    var league: League,
)