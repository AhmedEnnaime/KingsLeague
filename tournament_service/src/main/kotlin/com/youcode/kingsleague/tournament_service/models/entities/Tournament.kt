package com.youcode.kingsleague.tournament_service.models.entities

import com.youcode.kingsleague.tournament_service.models.transients.Organizer
import jakarta.persistence.*
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import lombok.Builder
import lombok.Getter
import lombok.Setter
import java.time.LocalDate

@Entity
@Table(name = "tournaments")
@Builder
@Getter
@Setter
data class Tournament(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column(nullable = false) @NotNull(message = "name can't be null") var name: String,
    @Column(nullable = false) @Future @NotNull(message = "debut date can't be null") var debutDate: LocalDate,
    @Column(nullable = false) @Future @NotNull(message = "end date can't be null") var endDate: LocalDate,
    @Column(nullable = false) @NotNull(message = "location can't be null") var location: String,
    @Column(nullable = false) var organizerId: Long,
    @Transient val organizer: Organizer
)