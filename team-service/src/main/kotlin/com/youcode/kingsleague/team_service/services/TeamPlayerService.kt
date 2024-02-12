package com.youcode.kingsleague.team_service.services

import com.youcode.kingsleague.team_service.models.dto.TeamPlayerDTO
import org.springframework.stereotype.Repository

@Repository
interface TeamPlayerService {

    fun save(teamPlayer: TeamPlayerDTO): TeamPlayerDTO
    fun findAll(): List<TeamPlayerDTO?>?
    fun findPlayersByTeam(teamId: Long): List<TeamPlayerDTO>
    fun findTeamsByPlayer(playerId: Long): List<TeamPlayerDTO>
}