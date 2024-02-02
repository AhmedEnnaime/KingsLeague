package com.youcode.kingsleague.match_service.models.dto

import com.youcode.kingsleague.match_service.models.enums.MatchStatus
import com.youcode.kingsleague.match_service.models.transients.Team
import lombok.Builder

@Builder
data class MatchDTO (
    var id: Long,
    var status: MatchStatus = MatchStatus.SCHEDULED,
    val stadium: StadiumDTO,
    val result: ResultDTO,
    val teamA: Team,
    val teamB: Team,
)