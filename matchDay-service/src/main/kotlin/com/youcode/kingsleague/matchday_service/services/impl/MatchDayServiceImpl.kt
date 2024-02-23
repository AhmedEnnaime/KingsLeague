package com.youcode.kingsleague.matchday_service.services.impl

import com.youcode.kingsleague.common.exceptions.ResourceNotFoundException
import com.youcode.kingsleague.matchday_service.models.dto.MatchDayDTO
import com.youcode.kingsleague.matchday_service.models.entities.MatchDay
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
        leagueServiceClient.findLeagueById(dto.league!!.id)
        val matchDayEntity: MatchDay = modelMapper.map(dto, MatchDay::class.java)
        val savedMatchDay: MatchDay = matchDayRepository.save(matchDayEntity)
        return modelMapper.map(savedMatchDay, MatchDayDTO::class.java)
    }

    override fun getAll(): List<MatchDayDTO?>? {
        val matchDays: List<MatchDay> = matchDayRepository.findAll()
        return matchDays.map { matchDay -> modelMapper.map(matchDay, MatchDayDTO::class.java) }
    }

    override fun update(identifier: Long, dto: MatchDayDTO): MatchDayDTO {
        val existingMatchDay: MatchDay = matchDayRepository.findById(identifier)
            .orElseThrow { ResourceNotFoundException("Stadium with id $identifier not found") }
        dto.updatedAt = LocalDateTime.now()
        existingMatchDay.apply {
            dto.let {
                this.date = it.date
                this.leagueId = it.league!!.id
            }
        }
        val updatedMatchDay: MatchDay = matchDayRepository.save(existingMatchDay)
        return modelMapper.map(updatedMatchDay, MatchDayDTO::class.java)
    }

    override fun delete(identifier: Long) {
        if (!matchDayRepository.existsById(identifier))
            throw ResourceNotFoundException("Stadium with id $identifier not found")
        matchDayRepository.deleteById(identifier)
    }

    override fun findByID(identifier: Long): MatchDayDTO? {
        val matchDay: MatchDay = matchDayRepository.findById(identifier).orElseThrow {
            ResourceNotFoundException("Stadium with id $identifier not found")
        }
        return modelMapper.map(matchDay, MatchDayDTO::class.java)
    }
}