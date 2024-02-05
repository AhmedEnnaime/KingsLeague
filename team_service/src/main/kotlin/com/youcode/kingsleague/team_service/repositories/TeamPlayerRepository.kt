package com.youcode.kingsleague.team_service.repositories

import com.youcode.kingsleague.team_service.models.embeddables.TeamPlayerKey
import com.youcode.kingsleague.team_service.models.entities.TeamPlayer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TeamPlayerRepository: JpaRepository<TeamPlayer, TeamPlayerKey> {
}