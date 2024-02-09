package com.youcode.kingsleague.tournamentteamsservice.models.entities

import com.youcode.kingsleague.tournamentteamsservice.models.embeddables.TournamentTeamKey
import com.youcode.kingsleague.tournamentteamsservice.models.transients.Team
import com.youcode.kingsleague.tournamentteamsservice.models.transients.Tournament
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Transient

@Entity
@Table(name = "tournament_teams")
data class TournamentTeam (
    @EmbeddedId var id: TournamentTeamKey,
    @Transient val team: Team,
    @Transient val tournament: Tournament
)