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
        tournamentType: String?, // Adjusted property name to match entity
        createdAt: LocalDateTime?,
        updatedAt: LocalDateTime?
    ) : super(id, name, debutDate, endDate, location, organizer, tournamentType, createdAt, updatedAt)
}
