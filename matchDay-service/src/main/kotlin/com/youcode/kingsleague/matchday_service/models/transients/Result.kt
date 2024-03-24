package com.youcode.kingsleague.matchday_service.models.transients

import java.time.LocalDateTime

data class Result (
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