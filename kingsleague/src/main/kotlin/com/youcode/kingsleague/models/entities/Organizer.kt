package com.youcode.kingsleague.models.entities

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import lombok.Builder

@Entity
@Table(name = "organizers")
@Builder
class Organizer(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column(nullable = false) @NotNull(message = "name can't be null") var name: String,
    var phone: String,
) {
    @OneToMany var tournaments: List<Tournament> = listOf()
}
