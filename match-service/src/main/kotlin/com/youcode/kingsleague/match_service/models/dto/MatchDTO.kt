package com.youcode.kingsleague.match_service.models.dto

import com.youcode.kingsleague.match_service.models.enums.MatchStatus
import com.youcode.kingsleague.match_service.models.enums.MatchType
import com.youcode.kingsleague.match_service.models.transients.MatchDay
import com.youcode.kingsleague.match_service.models.transients.Team
import java.time.LocalDateTime
import java.time.LocalTime

data class MatchDTO (
    var id: Long?,
    var time: LocalTime,
    var status: MatchStatus = MatchStatus.SCHEDULED,
    var matchType: MatchType,
//    val stadium: StadiumDTO?,
    val stadiumId: Long,
    val result: ResultDTO?,
    val teamAId: Long,
    val teamBId: Long,
    val matchDayId: Long?,
    val roundId: Long?,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?
) {
    constructor() : this(null, LocalTime.now(), MatchStatus.SCHEDULED,MatchType.LEAGUE, 0, null, 0, 0, null, null, null, null)
}
