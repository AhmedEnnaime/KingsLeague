package com.youcode.kingsleague.match_service.models.transients

import com.youcode.kingsleague.match_service.models.embaddables.TournamentTeamKey
import java.time.LocalDateTime

data class TournamentTeam (
    var id: TournamentTeamKey?,
    var team: Team?,
    var tournament: Tournament?,
    var points: Int?,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?
) {
    constructor() : this(null, null, null, 0, null, null)
}