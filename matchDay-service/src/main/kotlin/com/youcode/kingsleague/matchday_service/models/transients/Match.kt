package com.youcode.kingsleague.matchday_service.models.transients

import java.time.LocalDateTime
import java.time.LocalTime

data class Match (
    var id: Long?,
    var time: LocalTime,
    var status: String?,
    var matchType: String?,
//    val stadium: StadiumDTO?,
    val stadiumId: Long?,
    val opponentAId: Long,
    val opponentBId: Long,
    val matchDayId: Long?,
    var teamA: Team?,
    var teamB: Team?,
    val roundId: Long?,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?
) {
    constructor() : this(null, LocalTime.now(), null,null, null, 0, 0, 0, null, null, null, null, null)
}