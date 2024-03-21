package com.youcode.kingsleague.match_service.models.transients

import java.time.LocalDate

data class Round (
    var id: Long,
    var date: LocalDate,
    var cup: Cup
)