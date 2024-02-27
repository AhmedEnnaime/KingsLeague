package com.youcode.kingsleague.tournamentservice.models.dto

import com.youcode.kingsleague.tournamentservice.models.transients.Organizer
import java.time.LocalDate
import java.time.LocalDateTime


class LeagueDTO : TournamentDTO {

    constructor() : super(null, "", LocalDate.now(), LocalDate.now(), "", 0, null, "", null, null)

    constructor(
        id: Long?,
        name: String,
        debutDate: LocalDate,
        endDate: LocalDate,
        location: String,
        teamsNum: Int,
        organizer: Organizer?,
        tournamentType: String?,
        createdAt: LocalDateTime?,
        updatedAt: LocalDateTime?
    ) : super(id, name, debutDate, endDate, location, teamsNum, organizer, tournamentType, createdAt, updatedAt)
}
