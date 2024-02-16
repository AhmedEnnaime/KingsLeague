package com.youcode.kingsleague.tournamentservice.models.dto

import com.youcode.kingsleague.tournamentservice.models.transients.Organizer
import java.time.LocalDate
import java.time.LocalDateTime

class CupDTO : TournamentDTO {

    constructor() : super(null, "", LocalDate.now(), LocalDate.now(), "", null, "", null, null)

    constructor(
        id: Long?,
        name: String,
        debutDate: LocalDate,
        endDate: LocalDate,
        location: String,
        organizer: Organizer?,
        tournament_type: String?,
        createdAt: LocalDateTime?,
        updatedAt: LocalDateTime?
    ) : super(id, name, debutDate, endDate, location, organizer, tournament_type, createdAt, updatedAt)
}
