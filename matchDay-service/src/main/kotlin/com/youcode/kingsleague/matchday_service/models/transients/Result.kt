package com.youcode.kingsleague.matchday_service.models.transients

import lombok.Builder

@Builder
data class Result (
    var id: Long,
    var score: String,
    var winner: Team,
    var match: Match
)