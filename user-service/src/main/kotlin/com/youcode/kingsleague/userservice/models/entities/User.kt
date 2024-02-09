package com.youcode.kingsleague.userservice.models.entities

import com.youcode.kingsleague.userservice.models.transients.Tournament
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Transient
import jakarta.validation.constraints.NotNull
import lombok.Builder

@Entity
@Table(name = "users")
@Builder
data class User (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column(nullable = false) @NotNull(message = "username can't be null") var username: String,
    @Column(nullable = false) @NotNull(message = "email can't be null") var email: String,
    @Column(nullable = false) @NotNull(message = "password can't be null") var password: String,
    var phone: String,
){
    @Transient val tournaments: List<Tournament> = listOf()
}