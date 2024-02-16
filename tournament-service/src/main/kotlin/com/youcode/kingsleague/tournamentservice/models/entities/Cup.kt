package com.youcode.kingsleague.tournamentservice.models.entities

import com.youcode.kingsleague.tournamentservice.models.transients.Organizer
import com.youcode.kingsleague.tournamentservice.models.transients.Round
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Transient
import lombok.Builder
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@DiscriminatorValue("CUP")
class Cup (
    id: Long,
    name: String,
    debutDate: LocalDate,
    endDate: LocalDate,
    location: String,
    organizerId: Long,
    organizer: Organizer,
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime
): Tournament(id, name, debutDate, endDate, location, organizerId, organizer, createdAt, updatedAt) {
    @Transient val rounds: List<Round> = listOf()
}
