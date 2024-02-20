package com.youcode.kingsleague.match_service.models.dto

import com.youcode.kingsleague.match_service.models.transients.Team
import java.time.LocalDateTime

data class ResultDTO (
    var id: Long?,
    var score: String,
    val winner: Team?,
    val match: MatchDTO?,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?
){
    constructor() : this(null, "", null, null, null, null)
}