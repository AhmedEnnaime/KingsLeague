package com.youcode.kingsleague.tournament_service.models.dto

import com.youcode.kingsleague.tournament_service.models.transients.Organizer
import lombok.Builder
import lombok.Getter
import lombok.Setter
import java.time.LocalDate

@Getter
@Setter
@Builder
data class TournamentDTO (
    val name: String,
    val debutDate: LocalDate,
    val endDate: LocalDate,
    val location: String,
    val organizer: Organizer,
)