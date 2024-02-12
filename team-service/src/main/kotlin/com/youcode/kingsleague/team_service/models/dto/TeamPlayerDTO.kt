package com.youcode.kingsleague.team_service.models.dto

import com.youcode.kingsleague.team_service.models.embeddables.TeamPlayerKey
import lombok.Builder
import java.time.LocalDateTime

@Builder
data class TeamPlayerDTO (
    var id: TeamPlayerKey?,
    var player: PlayerDTO,
    var team: TeamDTO,
    var joinedAt: LocalDateTime?,
    var updatedAt: LocalDateTime?,
){
    constructor() : this(null, PlayerDTO(), TeamDTO(), null, null)
}