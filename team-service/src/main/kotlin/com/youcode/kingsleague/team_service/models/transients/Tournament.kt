package com.youcode.kingsleague.team_service.models.transients

import java.time.LocalDate
import java.time.LocalDateTime

data class Tournament (
    var id: Long,
    var name: String,
    var debutDate: LocalDate,
    var endDate: LocalDate,
    var location: String,
    var teamsNum: Int,
//    var organizer: Organizer,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
)