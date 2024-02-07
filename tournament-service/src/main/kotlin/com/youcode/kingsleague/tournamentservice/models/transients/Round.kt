package com.youcode.kingsleague.tournamentservice.models.transients

import com.youcode.kingsleague.tournamentservice.models.entities.Cup
import com.youcode.kingsleague.tournamentservice.models.transients.Match
import lombok.Builder
import java.time.LocalDate

@Builder
data class Round (
    var id: Long,
    var date: LocalDate,
    var match: Match,
    var cup: Cup
)