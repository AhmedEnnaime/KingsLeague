package com.youcode.kingsleague.models.entities

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import lombok.Builder
import java.time.LocalDate

@Entity
@Table(name = "players")
@Builder
class Player (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column(nullable = false) @NotNull(message = "first name can't be null") var firstName: String,
    @Column(nullable = false) @NotNull(message = "last name can't be null") var lastName: String,
    @Column(nullable = false) @Temporal(TemporalType.DATE) var birthday: LocalDate,
    @Column(nullable = false) @NotNull(message = "nationality can't be null") var nationality: String,
    @ManyToOne @JoinColumn(name = "team_id") val team: Team
)