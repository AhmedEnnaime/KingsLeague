package com.youcode.kingsleague.match_service.models.dto

import com.youcode.kingsleague.match_service.models.enums.MatchStatus
import com.youcode.kingsleague.match_service.models.transients.MatchDay
import com.youcode.kingsleague.match_service.models.transients.Team
import java.time.LocalDateTime

data class MatchDTO (
    var id: Long?,
    var time: LocalDateTime,
    var status: MatchStatus = MatchStatus.SCHEDULED,
    val stadium: StadiumDTO?,
    val result: ResultDTO?,
    val teamA: Team?,
    val teamB: Team?,
    val matchDay: MatchDay?,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?
) {
    constructor() : this(null, LocalDateTime.now(), MatchStatus.SCHEDULED, null, null, null, null, null, null, null)
}
