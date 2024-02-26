package com.youcode.kingsleague.matchday_service.services.impl

import com.youcode.kingsleague.common.exceptions.ResourceNotFoundException
import com.youcode.kingsleague.matchday_service.models.dto.MatchDayDTO
import com.youcode.kingsleague.matchday_service.models.entities.MatchDay
import com.youcode.kingsleague.matchday_service.models.transients.League
import com.youcode.kingsleague.matchday_service.repositories.MatchDayRepository
import com.youcode.kingsleague.matchday_service.services.MatchDayService
import com.youcode.kingsleague.matchday_service.services.client.LeagueServiceClient
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MatchDayServiceImpl(private val matchDayRepository: MatchDayRepository, private val modelMapper: ModelMapper, private val leagueServiceClient: LeagueServiceClient): MatchDayService {
    override fun save(dto: MatchDayDTO): MatchDayDTO {
        dto.createdAt = LocalDateTime.now()
        dto.updatedAt = LocalDateTime.now()
        val league: League = leagueServiceClient.findLeagueById(dto.leagueId)
        dto.league = league
        val matchDayEntity: MatchDay = modelMapper.map(dto, MatchDay::class.java)
        val savedMatchDay: MatchDay = matchDayRepository.save(matchDayEntity)
        return modelMapper.map(savedMatchDay, MatchDayDTO::class.java)
    }

    override fun getAll(): List<MatchDayDTO?>? {
        val matchDays: List<MatchDay> = matchDayRepository.findAll()
        return matchDays.map { matchDay ->
            val league: League = leagueServiceClient.findLeagueById(matchDay.leagueId)
            val matchDayDTO: MatchDayDTO = modelMapper.map(matchDay, MatchDayDTO::class.java)
            matchDayDTO.league = league
            matchDayDTO
        }
    }

    override fun update(identifier: Long, dto: MatchDayDTO): MatchDayDTO {
        val existingMatchDay: MatchDay = matchDayRepository.findById(identifier)
            .orElseThrow { ResourceNotFoundException("MatchDay with id $identifier not found") }
        leagueServiceClient.findLeagueById(dto.leagueId)
        existingMatchDay.apply {
            this.date = dto.date
            this.leagueId = dto.leagueId
            this.updatedAt = LocalDateTime.now()
        }
        val updatedMatchDay: MatchDay = matchDayRepository.save(existingMatchDay)
        return modelMapper.map(updatedMatchDay, MatchDayDTO::class.java)
    }

    override fun delete(identifier: Long) {
        if (!matchDayRepository.existsById(identifier))
            throw ResourceNotFoundException("MatchDay with id $identifier not found")
        matchDayRepository.deleteById(identifier)
    }

    override fun findByID(identifier: Long): MatchDayDTO? {
        val matchDay: MatchDay = matchDayRepository.findById(identifier).orElseThrow {
            ResourceNotFoundException("MatchDay with id $identifier not found")
        }
        val matchDayDTO: MatchDayDTO = modelMapper.map(matchDay, MatchDayDTO::class.java)
        matchDayDTO.league = leagueServiceClient.findLeagueById(matchDay.leagueId)
        return matchDayDTO
    }
}