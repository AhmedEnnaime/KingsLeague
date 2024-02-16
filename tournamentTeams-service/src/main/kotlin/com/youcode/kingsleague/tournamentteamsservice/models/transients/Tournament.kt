package com.youcode.kingsleague.tournamentteamsservice.models.transients

import lombok.Builder
import java.time.LocalDate

@Builder
data class Tournament (
    var id: Long?,
    var name: String = "",
    var debutDate: LocalDate = LocalDate.now(),
    var endDate: LocalDate = LocalDate.now(),
    var location: String = ""
) {
    constructor() : this(null, "", LocalDate.now(), LocalDate.now(), "")
}
