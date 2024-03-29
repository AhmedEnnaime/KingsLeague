package com.youcode.kingsleague.match_service.models.dto

import com.youcode.kingsleague.match_service.models.enums.MatchStatus
import com.youcode.kingsleague.match_service.models.enums.MatchType
import com.youcode.kingsleague.match_service.models.transients.MatchDay
import com.youcode.kingsleague.match_service.models.transients.Round
import com.youcode.kingsleague.match_service.models.transients.Team
import java.time.LocalDateTime
import java.time.LocalTime

class RetrievalMatchDTO(
    var id: Long?,
    var time: LocalTime,
    var status: MatchStatus = MatchStatus.SCHEDULED,
    var matchType: MatchType,
    var stadium: StadiumDTO?,
    val result: ResultDTO?,
    var teamA: Team?,
    var teamB: Team?,
    var matchDay: MatchDay?,
    var round: Round?,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?
) {
    constructor() : this(null, LocalTime.now(), MatchStatus.SCHEDULED,
        MatchType.LEAGUE, null, null, null, null, null, null, null, null)
}