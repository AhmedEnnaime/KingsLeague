package com.youcode.kingsleague.tournamentteamsservice.models.dto

import com.youcode.kingsleague.tournamentteamsservice.models.embeddables.TournamentTeamKey
import com.youcode.kingsleague.tournamentteamsservice.models.transients.Team
import com.youcode.kingsleague.tournamentteamsservice.models.transients.Tournament
import lombok.Builder

@Builder
data class TournamentTeamDTO (
    var id: TournamentTeamKey?,
    var team: Team,
    var tournament: Tournament
) {
    constructor() : this(null, Team(), Tournament())
}
