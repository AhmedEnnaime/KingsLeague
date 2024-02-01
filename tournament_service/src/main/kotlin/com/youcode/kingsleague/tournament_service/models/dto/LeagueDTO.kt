package com.youcode.kingsleague.tournament_service.models.dto

import com.youcode.kingsleague.tournament_service.models.transients.Organizer
import lombok.Builder
import java.time.LocalDate

@Builder
class LeagueDTO(
    id: Long,
    name: String,
    debutDate: LocalDate,
    endDate: LocalDate,
    location: String,
    organizer: Organizer
) : TournamentDTO(id, name, debutDate, endDate, location, organizer)