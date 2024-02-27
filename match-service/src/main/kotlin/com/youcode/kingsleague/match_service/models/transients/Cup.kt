package com.youcode.kingsleague.match_service.models.transients

import lombok.Builder
import java.time.LocalDate
import java.time.LocalDateTime

@Builder
data class Cup (
    var id: Long,
    var name: String,
    var debutDate: LocalDate,
    var endDate: LocalDate,
    var location: String,
    var teamsNum: Int,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
)