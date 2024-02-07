package com.youcode.kingsleague.tournamentservice.models.transients

import lombok.Builder
import java.time.LocalDateTime

@Builder
data class Match (
    var id: Long,
    var date: LocalDateTime,
//    var stadium: Stadium,
//    var result: Result,
    var teamA: Team,
    var teamB: Team,
)