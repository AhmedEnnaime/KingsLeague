package com.youcode.kingsleague.tournament_service.models.transients

import lombok.Builder
import lombok.Getter
import lombok.Setter

@Getter
@Setter
@Builder
data class Organizer(
    val username: String,
    val email: String,
    val password: String,
    val phone: String?
)