package com.youcode.kingsleague.round_service.models.transients

import lombok.Builder
import java.time.LocalDateTime

@Builder
data class Team (
    var id: Long,
    var name: String,
    var country: String,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime
)