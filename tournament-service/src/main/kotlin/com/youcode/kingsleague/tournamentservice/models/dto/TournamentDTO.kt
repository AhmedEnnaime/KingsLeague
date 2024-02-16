package com.youcode.kingsleague.tournamentservice.models.dto

import com.youcode.kingsleague.tournamentservice.models.transients.Organizer
import lombok.Builder
import java.time.LocalDate
import java.time.LocalDateTime

open class TournamentDTO(
    var id: Long?,
    var name: String,
    var debutDate: LocalDate,
    var endDate: LocalDate,
    var location: String,
    var organizer: Organizer?,
    var tournamentType: String?,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?
) {
    constructor() : this(null, "", LocalDate.now(), LocalDate.now(), "", null, "", null, null)
}