package com.youcode.kingsleague.match_service.models.dto

import java.time.LocalDateTime

data class StadiumDTO (
    var id: Long?,
    var name: String,
    var location: String,
    var capacity: Double,
    var matches: List<MatchDTO>?,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?
){
    constructor() : this(null, "", "", 0.00, null, null, null)
}