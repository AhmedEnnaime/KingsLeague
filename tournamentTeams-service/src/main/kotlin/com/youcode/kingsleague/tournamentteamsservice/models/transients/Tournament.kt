package com.youcode.kingsleague.tournamentteamsservice.models.transients

import java.time.LocalDate
import java.time.LocalDateTime

data class Tournament (
    var id: Long?,
    var name: String,
    var debutDate: LocalDate,
    var endDate: LocalDate,
    var location: String,
    var tournamentType: String?,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?
) {
    constructor() : this(null, "", LocalDate.now(), LocalDate.now(), "", "", null, null)
}
