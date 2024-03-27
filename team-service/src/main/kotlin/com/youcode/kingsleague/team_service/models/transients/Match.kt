package com.youcode.kingsleague.team_service.models.transients

import com.youcode.kingsleague.team_service.models.dto.TeamDTO
import java.time.LocalDateTime

data class Match (
    var id: Long,
    var date: LocalDateTime,
    var stadium: Stadium,
    var result: Result,
    var teamA: TeamDTO,
    var teamB: TeamDTO,
)