package com.youcode.kingsleague.tournamentservice.models.dto

import com.youcode.kingsleague.tournamentservice.models.transients.Organizer
import lombok.Builder
import java.time.LocalDate
import java.time.LocalDateTime

@Builder
open class TournamentDTO (
    var id: Long,
    var name: String,
    var debutDate: LocalDate,
    var endDate: LocalDate,
    var location: String,
    var organizer: Organizer,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?,
)