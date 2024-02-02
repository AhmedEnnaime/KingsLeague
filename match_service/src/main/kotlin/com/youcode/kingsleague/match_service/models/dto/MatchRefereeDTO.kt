package com.youcode.kingsleague.match_service.models.dto

import lombok.Builder

@Builder
data class MatchRefereeDTO (
    val match: MatchDTO,
    val referee: RefereeDTO,
    var notes: String
)