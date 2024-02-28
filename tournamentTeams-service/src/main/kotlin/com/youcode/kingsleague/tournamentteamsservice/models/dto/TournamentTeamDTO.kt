package com.youcode.kingsleague.tournamentteamsservice.models.dto

import com.youcode.kingsleague.tournamentteamsservice.models.embeddables.TournamentTeamKey
import com.youcode.kingsleague.tournamentteamsservice.models.transients.Team
import com.youcode.kingsleague.tournamentteamsservice.models.transients.Tournament
import java.time.LocalDateTime

data class TournamentTeamDTO (
    var id: TournamentTeamKey?,
    var team: Team?,
    var tournament: Tournament?,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?
) {
    constructor() : this(null, null, null, null, null)
}
