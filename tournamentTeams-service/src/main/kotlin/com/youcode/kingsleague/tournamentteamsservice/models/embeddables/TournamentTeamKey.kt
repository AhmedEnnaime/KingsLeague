package com.youcode.kingsleague.tournamentteamsservice.models.embeddables

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class TournamentTeamKey (
    @Column(name = "team_id") var teamId: Long,
    @Column(name = "tournament_id") var tournamentId: Long
): Serializable