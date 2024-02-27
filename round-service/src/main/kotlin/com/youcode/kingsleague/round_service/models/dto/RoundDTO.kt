package com.youcode.kingsleague.round_service.models.dto

import com.youcode.kingsleague.round_service.models.transients.Cup
import com.youcode.kingsleague.round_service.models.transients.Match
import java.time.LocalDate
import java.time.LocalDateTime

data class RoundDTO (
    var id: Long?,
    var date: LocalDate,
    var cup: Cup?,
    var tournamentId: Long,
    val matches: List<Match>?,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?
) {
    constructor(): this(null, LocalDate.now(), null, 0, null, null, null)
}