package com.youcode.kingsleague.tournamentservice.models.transients

import com.youcode.kingsleague.tournamentservice.models.embddables.TournamentTeamKey
import lombok.Builder

@Builder
data class TournamentTeam (
    var id: TournamentTeamKey,
    var team: Team,
    var tournament: Tournament
)