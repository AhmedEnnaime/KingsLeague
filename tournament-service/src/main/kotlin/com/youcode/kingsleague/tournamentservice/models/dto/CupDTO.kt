package com.youcode.kingsleague.tournamentservice.models.dto

import com.youcode.kingsleague.tournamentservice.models.dto.TournamentDTO
import com.youcode.kingsleague.tournamentservice.models.transients.Organizer
import lombok.Builder
import java.time.LocalDate
import java.time.LocalDateTime

@Builder
class CupDTO(
    id: Long,
    name: String,
    debutDate: LocalDate,
    endDate: LocalDate,
    location: String,
    organizer: Organizer,
    createdAt: LocalDateTime?,
    updatedAt: LocalDateTime?,
) : TournamentDTO(id, name, debutDate, endDate, location, organizer, createdAt, updatedAt)