package com.youcode.kingsleague.tournament_service.models.transients

import lombok.Builder

@Builder
data class Organizer(
    val username: String,
    val email: String,
    val password: String,
    val phone: String?
)