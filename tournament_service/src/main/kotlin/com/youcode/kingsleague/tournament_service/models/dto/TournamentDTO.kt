package com.youcode.kingsleague.tournament_service.models.dto

import com.youcode.kingsleague.tournament_service.models.transients.Organizer
import com.youcode.kingsleague.tournament_service.models.transients.Team
import java.time.LocalDate
import java.time.LocalDateTime


open class TournamentDTO (
    var id: Long,
    var name: String,
    var debutDate: LocalDate,
    var endDate: LocalDate,
    var location: String,
    var organizer: Organizer,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
) {
    var teams: List<Team> = listOf()
}