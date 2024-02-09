package com.youcode.kingsleague.userservice.models.transients

import com.youcode.kingsleague.userservice.models.dto.UserDTO
import lombok.Builder
import java.time.LocalDate
import java.time.LocalDateTime

@Builder
data class Tournament (
    var id: Long,
    var name: String,
    var debutDate: LocalDate,
    var endDate: LocalDate,
    var location: String,
    var organizer: UserDTO,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
)