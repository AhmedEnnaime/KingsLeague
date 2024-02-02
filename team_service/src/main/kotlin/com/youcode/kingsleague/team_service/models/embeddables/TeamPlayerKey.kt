package com.youcode.kingsleague.team_service.models.embeddables

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import lombok.Builder
import java.io.Serializable

@Embeddable
@Builder
data class TeamPlayerKey (
    @Column(name = "team_id") var teamId: Long,
    @Column(name = "player_id") var playedId: Long
): Serializable