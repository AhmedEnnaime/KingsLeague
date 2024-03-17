package com.youcode.kingsleague.team_service.services

import com.youcode.kingsleague.team_service.models.dto.PlayerDTO
import com.youcode.kingsleague.team_service.models.dto.TeamDTO
import com.youcode.kingsleague.team_service.models.dto.TeamPlayerDTO
import org.springframework.stereotype.Repository

@Repository
interface TeamPlayerService {

    fun save(teamPlayer: TeamPlayerDTO): TeamPlayerDTO
    fun findAll(): List<TeamPlayerDTO?>?
    fun findPlayersByTeam(teamId: Long): List<PlayerDTO>
    fun findTeamsByPlayer(playerId: Long): List<TeamDTO>
    fun removePlayerFromTeam(teamId: Long, playerId: Long)
}