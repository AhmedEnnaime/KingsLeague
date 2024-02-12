package com.youcode.kingsleague.team_service.services.impl

import com.youcode.kingsleague.common.exceptions.ResourceNotFoundException
import com.youcode.kingsleague.team_service.models.dto.TeamDTO
import com.youcode.kingsleague.team_service.models.entities.Team
import com.youcode.kingsleague.team_service.repositories.TeamRepository
import com.youcode.kingsleague.team_service.services.TeamService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TeamServiceImpl(private val teamRepository: TeamRepository, private val modelMapper: ModelMapper): TeamService {
    override fun save(dto: TeamDTO): TeamDTO {
        dto.createdAt = LocalDateTime.now()
        dto.updatedAt = LocalDateTime.now()
        val teamEntity: Team = modelMapper.map(dto, Team::class.java)
        val savedTeam: Team = teamRepository.save(teamEntity)
        return modelMapper.map(savedTeam, TeamDTO::class.java)
    }

    override fun getAll(): List<TeamDTO?>? {
        val teams: List<Team> = teamRepository.findAll()
        return teams.map { team -> modelMapper.map(team, TeamDTO::class.java) }
    }

    override fun update(identifier: Long, dto: TeamDTO): TeamDTO {
        val existingTeam: Team = teamRepository.findById(identifier)
            .orElseThrow { ResourceNotFoundException("Team with id $identifier not found") }

        dto.updatedAt = LocalDateTime.now()
        existingTeam.apply {
            dto.let {
                this.name = it.name
                this.country = it.country
            }
        }
        val updatedTeam: Team = teamRepository.save(existingTeam)
        return modelMapper.map(updatedTeam, TeamDTO::class.java)
    }

    override fun delete(identifier: Long) {
        if (!teamRepository.existsById(identifier))
            throw ResourceNotFoundException("Team with id $identifier not found")
        teamRepository.deleteById(identifier)
    }

    override fun findByID(identifier: Long): TeamDTO? {
        val team: Team = teamRepository.findById(identifier).orElseThrow {
            ResourceNotFoundException("Team with id $identifier not found")
        }
        return modelMapper.map(team, TeamDTO::class.java)
    }

}