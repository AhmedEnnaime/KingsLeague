package com.youcode.kingsleague.team_service.models.transients

import com.youcode.kingsleague.team_service.models.dto.TeamDTO
import lombok.Builder

@Builder
data class Result (
    var id: Long,
    var score: String,
    var team: TeamDTO,
    var match: Match
)