package com.youcode.kingsleague.tournamentservice.services.impl

import com.youcode.kingsleague.common.exceptions.ResourceNotFoundException
import com.youcode.kingsleague.tournamentservice.models.dto.LeagueDTO
import com.youcode.kingsleague.tournamentservice.models.entities.League
import com.youcode.kingsleague.tournamentservice.repositories.LeagueRepository
import com.youcode.kingsleague.tournamentservice.services.LeagueService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class LeagueServiceImpl(private val leagueRepository: LeagueRepository, private val modelMapper: ModelMapper): LeagueService {
    override fun save(dto: LeagueDTO): LeagueDTO {
        dto.createdAt = LocalDateTime.now()
        dto.updatedAt = LocalDateTime.now()
        val leagueEntity: League = modelMapper.map(dto, League::class.java)
        val savedLeague: League = leagueRepository.save(leagueEntity)
        return modelMapper.map(savedLeague, LeagueDTO::class.java)
    }

    override fun getAll(): List<LeagueDTO?>? {
        val leagues: List<League> = leagueRepository.findAll()
        return leagues.map { league -> modelMapper.map(league, LeagueDTO::class.java) }
    }

    override fun update(identifier: Long, dto: LeagueDTO): LeagueDTO {
        val existingLeague: League = leagueRepository.findById(identifier)
            .orElseThrow { ResourceNotFoundException("League with id $identifier not found") }
        dto.updatedAt = LocalDateTime.now()
        existingLeague.apply {
            dto.let {
                this.name = it.name
                this.location = it.location
                this.debutDate = it.debutDate
                this.endDate = it.endDate
            }
        }
        val updatedLeague: League = leagueRepository.save(existingLeague)
        return modelMapper.map(updatedLeague, LeagueDTO::class.java)
    }

    override fun delete(identifier: Long) {
        if (!leagueRepository.existsById(identifier))
            throw ResourceNotFoundException("League with id $identifier not found")
        leagueRepository.deleteById(identifier)
    }

    override fun findByID(identifier: Long): LeagueDTO? {
        val league: League = leagueRepository.findById(identifier).orElseThrow {
            ResourceNotFoundException("League with id $identifier not found")
        }
        return modelMapper.map(league, LeagueDTO::class.java)
    }
}