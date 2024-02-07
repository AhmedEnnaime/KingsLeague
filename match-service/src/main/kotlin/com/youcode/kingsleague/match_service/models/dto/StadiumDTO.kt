package com.youcode.kingsleague.match_service.models.dto

import lombok.Builder

@Builder
data class StadiumDTO (
    var id: Long,
    var name: String,
    var location: String,
    var capacity: Int,
    var matches: List<MatchDTO>
)