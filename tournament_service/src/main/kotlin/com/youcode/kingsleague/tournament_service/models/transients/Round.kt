package com.youcode.kingsleague.tournament_service.models.transients

import com.youcode.kingsleague.tournament_service.models.entities.Cup
import lombok.Builder
import java.time.LocalDate

@Builder
data class Round (
    var id: Long,
    var date: LocalDate,
    var match: Match,
    var cup: Cup
)