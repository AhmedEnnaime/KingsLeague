package com.youcode.kingsleague.team_service.models.transients

import com.youcode.kingsleague.team_service.models.embeddables.TournamentTeamKey
import com.youcode.kingsleague.team_service.models.entities.Team
import lombok.Builder

@Builder
data class TournamentTeam (
    var id: TournamentTeamKey,
    var team: Team,
    var tournament: Tournament
)