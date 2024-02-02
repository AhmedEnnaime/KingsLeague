package com.youcode.kingsleague.team_service.models.transients

import com.youcode.kingsleague.team_service.models.dto.TeamDTO
import lombok.Builder
import java.time.LocalDateTime

@Builder
data class Match (
    var id: Long,
    var date: LocalDateTime,
    var stadium: Stadium,
    var result: Result,
    var teamA: TeamDTO,
    var teamB: TeamDTO,
)