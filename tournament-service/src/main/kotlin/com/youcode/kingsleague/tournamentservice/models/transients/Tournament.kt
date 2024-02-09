package com.youcode.kingsleague.tournamentservice.models.transients

import java.time.LocalDate

data class Tournament (
    var id: Long,
    var name: String,
    var debutDate: LocalDate,
    var endDate: LocalDate,
    var location: String,
    var organizer: Organizer,
)