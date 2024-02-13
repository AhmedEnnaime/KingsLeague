package com.youcode.kingsleague.team_service.services.impl

import com.youcode.kingsleague.common.exceptions.ResourceNotFoundException
import com.youcode.kingsleague.team_service.models.dto.PlayerDTO
import com.youcode.kingsleague.team_service.models.dto.TeamDTO
import com.youcode.kingsleague.team_service.models.dto.TeamPlayerDTO
import com.youcode.kingsleague.team_service.models.embeddables.TeamPlayerKey
import com.youcode.kingsleague.team_service.models.entities.TeamPlayer
import com.youcode.kingsleague.team_service.repositories.TeamPlayerRepository
import com.youcode.kingsleague.team_service.services.PlayerService
import com.youcode.kingsleague.team_service.services.TeamPlayerService
import com.youcode.kingsleague.team_service.services.TeamService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TeamPlayerServiceImpl(private val teamPlayerRepository: TeamPlayerRepository, private val teamService: TeamService, private val playerService: PlayerService, private val modelMapper: ModelMapper): TeamPlayerService {
    override fun save(teamPlayer: TeamPlayerDTO): TeamPlayerDTO {
        val teamId = teamPlayer.team.id ?: throw IllegalArgumentException("Team ID must not be null")
        teamService.findByID(teamId)
            ?: throw ResourceNotFoundException("Team with id $teamId not found")

        val playerId = teamPlayer.player.id ?: throw IllegalArgumentException("Player ID must not be null")
        playerService.findByID(playerId)
            ?: throw ResourceNotFoundException("Player with id $playerId not found")
        val teamPlayerKey = TeamPlayerKey(teamId, playerId)

        if (teamPlayerRepository.existsById(teamPlayerKey)){
            throw Exception("The player with id ${teamPlayerKey.teamId} is already in the team with id ${teamPlayerKey.teamId}")
        }else {
            teamPlayer.id = teamPlayerKey
        }

        teamPlayer.joinedAt = LocalDateTime.now()
        teamPlayer.updatedAt = LocalDateTime.now()
        val teamPlayerEntity: TeamPlayer = modelMapper.map(teamPlayer, TeamPlayer::class.java)
        return modelMapper.map(teamPlayerRepository.save(teamPlayerEntity), TeamPlayerDTO::class.java)
    }

    override fun findAll(): List<TeamPlayerDTO?>? {
        val teamPlayers: List<TeamPlayer> = teamPlayerRepository.findAll()
        return teamPlayers.map { teamPlayer -> modelMapper.map(teamPlayer, TeamPlayerDTO::class.java) }
    }

    override fun findPlayersByTeam(teamId: Long): List<PlayerDTO> {
        if (teamService.findByID(teamId) == null)
            throw ResourceNotFoundException("Team with id $teamId not found")
        val teamPlayers = teamPlayerRepository.findByTeamId(teamId)
        return teamPlayers.map { modelMapper.map(it.player, PlayerDTO::class.java) }
    }

    override fun findTeamsByPlayer(playerId: Long): List<TeamDTO> {
        if (playerId.let { playerService.findByID(it) } == null)
            throw ResourceNotFoundException("Player with id $playerId not found")
        val teamPlayers = teamPlayerRepository.findByPlayerId(playerId)
        return teamPlayers.map { modelMapper.map(it.team, TeamDTO::class.java) }
    }
}