package com.youcode.kingsleague.userservice.models.dto

import jakarta.validation.constraints.NotBlank
import lombok.Builder

@Builder
data class UserDTO (
    @NotBlank(message = "username can't be blank") val username: String,
    @NotBlank(message = "email can't be blank") val email: String,
    @NotBlank(message = "password can't be blank") val password: String,
    val phone: String?
)