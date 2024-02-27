package com.youcode.kingsleague.matchday_service.models.transients

import java.time.LocalDate
import java.time.LocalDateTime

data class League (
    var id: Long?,
    var name: String?,
    var debutDate: LocalDate?,
    var endDate: LocalDate?,
    var location: String?,
    var teamsNum: Int?,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?,
)