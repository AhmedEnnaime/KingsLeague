package com.youcode.kingsleague.team_service.services.impl

import com.youcode.kingsleague.common.config.MapperConfig
import com.youcode.kingsleague.team_service.models.dto.TeamDTO
import com.youcode.kingsleague.team_service.models.entities.Team
import com.youcode.kingsleague.team_service.repositories.TeamRepository
import com.youcode.kingsleague.team_service.services.TeamService
import org.springframework.stereotype.Service

@Service
class TeamServiceImpl(private val teamRepository: TeamRepository, private val modelMapper: MapperConfig): TeamService {
    override fun save(team: TeamDTO): TeamDTO {
        val teamEntity: Team = modelMapper.map(team, Team::class.java)
        val savedTeam: Team = teamRepository.save(teamEntity)
        return modelMapper.map(savedTeam, TeamDTO::class.java)
    }

    override fun findAll(): List<TeamDTO> {
        TODO("Not yet implemented")
    }

    override fun findByID(id: Long): TeamDTO {
        return teamRepository.findById(id).
        TODO("Not yet implemented")
    }

    override fun update(id: Long, team: TeamDTO): TeamDTO {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long) {
        TODO("Not yet implemented")
    }
}