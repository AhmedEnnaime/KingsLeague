package com.youcode.kingsleague.team_service.services.impl

import com.youcode.kingsleague.common.exceptions.ResourceNotFoundException
import com.youcode.kingsleague.team_service.models.dto.PlayerDTO
import com.youcode.kingsleague.team_service.models.dto.TeamDTO
import com.youcode.kingsleague.team_service.models.entities.Player
import com.youcode.kingsleague.team_service.models.entities.Team
import com.youcode.kingsleague.team_service.repositories.TeamRepository
import com.youcode.kingsleague.team_service.services.TeamService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class TeamServiceImpl(private val teamRepository: TeamRepository, private val modelMapper: ModelMapper): TeamService {
    override fun save(team: TeamDTO): TeamDTO {
        val teamEntity: Team = modelMapper.map(team, Team::class.java)
        val savedTeam: Team = teamRepository.save(teamEntity)
        return modelMapper.map(savedTeam, TeamDTO::class.java)
    }

    override fun getAll(): List<TeamDTO?>? {
        val teams: List<Team> = teamRepository.findAll()
        return teams.map { team -> modelMapper.map(team, TeamDTO::class.java) }
    }

    override fun update(id: Long, teamDTO: TeamDTO): TeamDTO {
        val existingTeam: Team = teamRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Team with id $id not found") }

        existingTeam.apply {
            teamDTO.let {
                this.name = it.name
                this.country = it.country
            }
        }

        val updatedTeam: Team = teamRepository.save(existingTeam)
        return modelMapper.map(updatedTeam, TeamDTO::class.java)
    }

    override fun delete(id: Long) {
        if (!teamRepository.existsById(id))
            throw ResourceNotFoundException("Team with id $id not found")
        teamRepository.deleteById(id)
    }

    override fun findByID(id: Long): TeamDTO? {
        val team: Team = teamRepository.findById(id).orElseThrow {
            ResourceNotFoundException("Team with id $id not found")
        }
        return modelMapper.map(team, TeamDTO::class.java)
    }

}