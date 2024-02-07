package com.youcode.kingsleague.tournamentservice.models.transients

import lombok.Builder

@Builder
data class Organizer(
    val username: String,
    val email: String,
    val password: String,
    val phone: String?
)