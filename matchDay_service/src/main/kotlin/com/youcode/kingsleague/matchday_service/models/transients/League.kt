package com.youcode.kingsleague.matchday_service.models.transients

import lombok.Builder
import java.time.LocalDate
import java.time.LocalDateTime

@Builder
data class League (
    var id: Long,
    var name: String,
    var debutDate: LocalDate,
    var endDate: LocalDate,
    var location: String,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
)