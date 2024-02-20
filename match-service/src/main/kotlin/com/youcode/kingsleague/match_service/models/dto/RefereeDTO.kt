package com.youcode.kingsleague.match_service.models.dto

import java.time.LocalDateTime

data class RefereeDTO (
    var id: Long?,
    var name: String,
    var nationality: String,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?
){
    constructor() : this(null, "", "", null, null)
}