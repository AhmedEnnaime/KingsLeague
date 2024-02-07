package com.youcode.kingsleague.team_service.models.dto

import lombok.Builder
import java.time.LocalDate
import java.time.LocalDateTime

@Builder
data class PlayerDTO (
    var firstName: String,
    var lastName: String,
    var weight: Double,
    var height: Double,
    var birthday: LocalDate,
    var nationality: String,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
)