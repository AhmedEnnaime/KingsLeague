package com.youcode.kingsleague.match_service.models.dto

import com.youcode.kingsleague.match_service.models.embaddables.MatchRefereeKey
import lombok.Builder

@Builder
data class MatchRefereeDTO (
    var id: MatchRefereeKey,
    val match: MatchDTO,
    val referee: RefereeDTO,
    var notes: String
)