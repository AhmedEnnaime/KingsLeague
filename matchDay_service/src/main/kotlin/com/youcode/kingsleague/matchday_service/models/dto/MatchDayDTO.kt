package com.youcode.kingsleague.matchday_service.models.dto

import com.youcode.kingsleague.matchday_service.models.transients.League
import com.youcode.kingsleague.matchday_service.models.transients.Match
import lombok.Builder
import java.time.LocalDate

@Builder
data class MatchDayDTO (
    var id: Long,
    var date: LocalDate,
    var league: League,
    var match: Match,
) {
    var matches: List<Match> = listOf()
}