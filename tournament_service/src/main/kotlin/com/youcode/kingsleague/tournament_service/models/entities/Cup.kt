package com.youcode.kingsleague.tournament_service.models.entities

import com.youcode.kingsleague.tournament_service.models.transients.Organizer
import jakarta.persistence.Entity
import jakarta.persistence.Table
import lombok.Builder
import java.time.LocalDate

@Entity
@Table(name = "cups")
@Builder
class Cup (id: Long, name: String, debutDate: LocalDate, endDate: LocalDate, location: String, organizerId: Long, organizer: Organizer): Tournament(id, name, debutDate, endDate, location, organizerId, organizer)