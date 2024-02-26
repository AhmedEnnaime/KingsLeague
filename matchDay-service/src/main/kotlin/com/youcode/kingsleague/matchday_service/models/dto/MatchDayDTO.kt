package com.youcode.kingsleague.matchday_service.models.dto

import com.youcode.kingsleague.matchday_service.models.transients.League
import com.youcode.kingsleague.matchday_service.models.transients.Match
import java.time.LocalDate
import java.time.LocalDateTime

data class MatchDayDTO (
    var id: Long?,
    var date: LocalDate,
    var league: League?,
    var leagueId: Long,
    var matches: List<Match>?,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?
) {
    constructor(): this(null, LocalDate.now(), null, 0, null, null, null)
}