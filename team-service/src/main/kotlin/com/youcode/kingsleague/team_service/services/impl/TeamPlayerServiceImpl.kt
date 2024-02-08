package com.youcode.kingsleague.team_service.services.impl

import com.youcode.kingsleague.common.exceptions.ResourceNotFoundException
import com.youcode.kingsleague.team_service.models.dto.PlayerDTO
import com.youcode.kingsleague.team_service.models.dto.TeamDTO
import com.youcode.kingsleague.team_service.models.dto.TeamPlayerDTO
import com.youcode.kingsleague.team_service.models.entities.TeamPlayer
import com.youcode.kingsleague.team_service.repositories.TeamPlayerRepository
import com.youcode.kingsleague.team_service.services.PlayerService
import com.youcode.kingsleague.team_service.services.TeamPlayerService
import com.youcode.kingsleague.team_service.services.TeamService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class TeamPlayerServiceImpl(private val teamPlayerRepository: TeamPlayerRepository, private val teamService: TeamService, private val playerService: PlayerService, private val modelMapper: ModelMapper): TeamPlayerService {
    override fun save(teamPlayer: TeamPlayerDTO): TeamPlayerDTO {
        val teamPlayerEntity: TeamPlayer = modelMapper.map(teamPlayer, TeamPlayer::class.java)
        return modelMapper.map(teamPlayerRepository.save(teamPlayerEntity), TeamPlayerDTO::class.java)
    }

    override fun findPlayersByTeam(team: TeamDTO): List<TeamPlayerDTO> {
        if (teamService.findByID(team.id) == null)
            throw ResourceNotFoundException("Team with id ${team.id} not found")
        val teamPlayers = teamPlayerRepository.findByTeamId(team.id)
        return teamPlayers.map { modelMapper.map(it.player, TeamPlayerDTO::class.java) }
    }

    override fun findTeamsByPlayer(player: PlayerDTO): List<TeamPlayerDTO> {
        if (player.id?.let { playerService.findByID(it) } == null)
            throw ResourceNotFoundException("Player with id ${player.id} not found")
        val teamPlayers = teamPlayerRepository.findByPlayerId(player.id!!)
        return teamPlayers.map { modelMapper.map(it.team, TeamPlayerDTO::class.java) }
    }
}