package com.youcode.kingsleague.team_service.models.dto

import lombok.Builder
import java.time.LocalDateTime

@Builder
data class TeamDTO (
    var id: Long?,
    var name: String,
    var country: String,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?
){
    constructor() : this(null, "", "", null, null)
}