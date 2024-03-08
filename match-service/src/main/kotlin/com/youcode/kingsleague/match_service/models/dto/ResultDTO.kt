package com.youcode.kingsleague.match_service.models.dto

import com.youcode.kingsleague.match_service.models.transients.Team
import java.time.LocalDateTime

data class ResultDTO (
    var id: Long?,
    var score: String,
    var winner: Team?,
    var teamId: Long?,
    var matchId: Long,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?
){
    constructor() : this(null, "", null, null, 0, null, null)
}