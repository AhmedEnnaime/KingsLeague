package com.youcode.kingsleague.team_service.models.dto

import lombok.Builder
import java.time.LocalDate
import java.time.LocalDateTime

@Builder
data class PlayerDTO (
    var id: Long?,
    var firstName: String,
    var lastName: String,
    var weight: Double,
    var height: Double,
    var birthday: LocalDate,
    var nationality: String,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?,
){
    constructor() : this(null, "", "", 0.0, 0.0, LocalDate.now(), "", LocalDateTime.now(), LocalDateTime.now())
}