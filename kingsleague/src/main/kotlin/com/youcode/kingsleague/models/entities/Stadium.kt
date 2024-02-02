package com.youcode.kingsleague.models.entities

import jakarta.persistence.*
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import lombok.Builder

@Entity
@Table(name = "stadiums")
@Builder
class Stadium (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column(nullable = false) @NotNull(message = "name can't be null") var name: String,
    @Column(nullable = false) @NotNull(message = "location can't be null") var location: String,
    @Column(nullable = false) @Min(value = 500, message = "capacity of a stadium should not be less than 500") var capacity: Int
) {
    @OneToMany var matches: List<Match> = listOf()
}