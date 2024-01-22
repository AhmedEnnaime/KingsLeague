package com.youcode.kingsleague.organizer_service.models.entities

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import lombok.Builder

@Entity
@Table(name = "organizers")
@Builder
class Organizer(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column(nullable = false) @NotNull(message = "name can't be null") var name: String,
    @Column(nullable = false) @NotNull(message = "email can't be null") var email: String,
    @Column(nullable = false) @NotNull(message = "password can't be null") var password: String,
    var phone: String,
)