package com.youcode.kingsleague.team_service.services

import com.youcode.kingsleague.team_service.models.dto.TeamDTO
import com.youcode.kingsleague.team_service.models.entities.Team

interface TeamService {

    fun save(team: TeamDTO): TeamDTO
    fun delete(id: Long)
    fun update(id: Long, teamDTO: TeamDTO): TeamDTO
    fun getAll(): List<TeamDTO?>?
    fun findByID(id: Long): TeamDTO?
}