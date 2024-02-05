package com.youcode.kingsleague.team_service.services

import com.youcode.kingsleague.team_service.models.dto.TeamDTO

interface TeamService {

    fun save(team: TeamDTO): TeamDTO
    fun findAll(): List<TeamDTO>
    fun findByID(id: Long): TeamDTO
    fun update(id: Long, team: TeamDTO): TeamDTO
    fun delete(id: Long)
}