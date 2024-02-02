package com.youcode.kingsleague.models.entities

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import lombok.Builder

@Entity
@Table(name = "referees")
@Builder
class Referee (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column(nullable = false) @NotNull(message = "name can't be null") var name: String,
    @Column(nullable = false) @NotNull(message = "nationality can't be null") var nationality: String
) {
    @OneToMany(mappedBy = "referee") val matchReferees: List<MatchReferees> = listOf()
}