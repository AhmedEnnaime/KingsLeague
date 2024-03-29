package com.youcode.kingsleague.tournamentservice.models.entities

import com.youcode.kingsleague.tournamentservice.models.transients.MatchDay
import com.youcode.kingsleague.tournamentservice.models.transients.Organizer
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import jakarta.persistence.Transient
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@DiscriminatorValue("LEAGUE")
class League (
    id: Long,
    name: String,
    debutDate: LocalDate,
    endDate: LocalDate,
    location: String,
    teamsNum: Int,
    organizerId: Long,
    organizer: Organizer,
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime
) : Tournament(id, name, debutDate, endDate, location, teamsNum, organizerId, organizer, createdAt, updatedAt) {
    @Transient val matchDays: List<MatchDay> = listOf()
}