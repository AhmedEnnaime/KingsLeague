package com.youcode.kingsleague.match_service.models.dto

import com.youcode.kingsleague.match_service.models.transients.Team
import lombok.Builder

@Builder
data class ResultDTO (
    var id: Long,
    var score: String,
    val winner: Team,
    val match: MatchDTO
)