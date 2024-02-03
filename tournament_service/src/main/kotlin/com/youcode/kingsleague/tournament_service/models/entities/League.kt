package com.youcode.kingsleague.tournament_service.models.entities

import com.youcode.kingsleague.tournament_service.models.transients.MatchDay
import com.youcode.kingsleague.tournament_service.models.transients.Organizer
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Transient
import lombok.Builder
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "leagues")
@Builder
class League (
    id: Long,
    name: String,
    debutDate: LocalDate,
    endDate: LocalDate,
    location: String,
    organizerId: Long,
    organizer: Organizer,
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime
) : Tournament(id, name, debutDate, endDate, location, organizerId, organizer, createdAt, updatedAt) {
    @Transient val matchDays: List<MatchDay> = listOf()
}