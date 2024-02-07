package com.youcode.kingsleague.team_service.models.transients

import lombok.Builder

@Builder
data class Stadium (
    var id: Long,
    var name: String,
    var location: String,
    var capacity: Int
)