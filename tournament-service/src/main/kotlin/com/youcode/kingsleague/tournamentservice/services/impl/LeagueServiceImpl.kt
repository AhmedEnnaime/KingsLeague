package com.youcode.kingsleague.tournamentservice.services.impl

import com.youcode.kingsleague.tournamentservice.models.dto.LeagueDTO
import com.youcode.kingsleague.tournamentservice.repositories.LeagueRepository
import com.youcode.kingsleague.tournamentservice.services.LeagueService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class LeagueServiceImpl(private val leagueRepository: LeagueRepository, private val modelMapper: ModelMapper): LeagueService {
    override fun save(dto: LeagueDTO): LeagueDTO {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<LeagueDTO?>? {
        TODO("Not yet implemented")
    }

    override fun update(identifier: Long, dto: LeagueDTO): LeagueDTO {
        TODO("Not yet implemented")
    }

    override fun delete(identifier: Long) {
        TODO("Not yet implemented")
    }

    override fun findByID(identifier: Long): LeagueDTO? {
        TODO("Not yet implemented")
    }
}