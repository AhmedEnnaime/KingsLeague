package com.youcode.kingsleague.tournamentteamsservice.models.transients

import lombok.Builder
import java.time.LocalDate

@Builder
data class Tournament (
    var id: Long,
    var name: String,
    var debutDate: LocalDate,
    var endDate: LocalDate,
    var location: String,
//    var organizer: Organizer,
)