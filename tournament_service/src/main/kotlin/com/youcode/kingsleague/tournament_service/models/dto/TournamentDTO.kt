package com.youcode.kingsleague.tournament_service.models.dto

import com.youcode.kingsleague.tournament_service.models.transients.Organizer
import java.time.LocalDate


open class TournamentDTO (
    val id: Long,
    val name: String,
    val debutDate: LocalDate,
    val endDate: LocalDate,
    val location: String,
    val organizer: Organizer,
)