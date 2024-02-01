package com.youcode.kingsleague.tournament_service.models.entities

import com.youcode.kingsleague.tournament_service.models.transients.Organizer
import jakarta.persistence.*
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import lombok.Builder
import lombok.Getter
import lombok.Setter
import java.time.LocalDate

@MappedSuperclass
open class Tournament(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @NotNull(message = "name can't be null") var name: String,
    @Temporal(TemporalType.DATE) @Future @NotNull(message = "debut date can't be null") var debutDate: LocalDate,
    @Temporal(TemporalType.DATE) @Future @NotNull(message = "end date can't be null") var endDate: LocalDate,
    @NotNull(message = "location can't be null") var location: String,
     var organizerId: Long,
    @Transient val organizer: Organizer
)