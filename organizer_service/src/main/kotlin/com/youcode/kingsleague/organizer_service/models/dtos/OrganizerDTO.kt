package com.youcode.kingsleague.organizer_service.models.dtos

import jakarta.validation.constraints.NotBlank
import lombok.Builder
import lombok.Getter
import lombok.Setter

@Getter
@Setter
@Builder
data class OrganizerDTO(
    @NotBlank(message = "name can't be blank") val name: String,
    @NotBlank(message = "email can't be blank") val email: String,
    @NotBlank(message = "password can't be blank") val password: String,
    val phone: String?
)