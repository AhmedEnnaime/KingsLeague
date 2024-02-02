package com.youcode.kingsleague.tournament_service.models.dto

import com.youcode.kingsleague.tournament_service.models.transients.Organizer
import java.time.LocalDate
import java.time.LocalDateTime


open class TournamentDTO (
    val id: Long,
    val name: String,
    val debutDate: LocalDate,
    val endDate: LocalDate,
    val location: String,
    val organizer: Organizer,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
)