package com.youcode.kingsleague.match_service.models.dto

import lombok.Builder

@Builder
data class RefereeDTO (
    var id: Long,
    var name: String,
    var nationality: String,
)