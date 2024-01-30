package com.youcode.kingsleague.organizer_service.models.entities

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import lombok.Builder

@Entity
@Table(name = "organizers")
@Builder
data class Organizer(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column(nullable = false) @NotNull(message = "username can't be null") var username: String,
    @Column(nullable = false) @NotNull(message = "email can't be null") var email: String,
    @Column(nullable = false) @NotNull(message = "password can't be null") var password: String,
    var phone: String,
)