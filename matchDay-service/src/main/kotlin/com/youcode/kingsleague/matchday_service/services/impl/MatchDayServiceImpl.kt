package com.youcode.kingsleague.matchday_service.services.impl

import com.youcode.kingsleague.common.exceptions.MatchDayAlreadyExistsException
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
    override fun findByTournamentId(tournamentId: Long): List<MatchDayDTO> {
        val matchDays: List<MatchDay> = matchDayRepository.findByTournamentId(tournamentId)
        return matchDays.map { matchDay ->
            val league: League = leagueServiceClient.findLeagueById(tournamentId)
            val matchDayDTO: MatchDayDTO = modelMapper.map(matchDay, MatchDayDTO::class.java)
            matchDayDTO.league = league
            matchDayDTO
        }
    }

    override fun save(dto: MatchDayDTO): MatchDayDTO {
        dto.createdAt = LocalDateTime.now()
        dto.updatedAt = LocalDateTime.now()
        val league: League = leagueServiceClient.findLeagueById(dto.tournamentId)
        dto.league = league
        if (matchDayRepository.findByDateAndTournamentId(dto.date, dto.tournamentId).isPresent) {
            throw MatchDayAlreadyExistsException("MatchDay already exists in this tournament")
        }
        val matchDayEntity: MatchDay = modelMapper.map(dto, MatchDay::class.java)
        val savedMatchDay: MatchDay = matchDayRepository.save(matchDayEntity)
        return modelMapper.map(savedMatchDay, MatchDayDTO::class.java)
    }

    override fun getAll(): List<MatchDayDTO?>? {
        val matchDays: List<MatchDay> = matchDayRepository.findAll()
        return matchDays.map { matchDay ->
            val league: League = leagueServiceClient.findLeagueById(matchDay.tournamentId)
            val matchDayDTO: MatchDayDTO = modelMapper.map(matchDay, MatchDayDTO::class.java)
            matchDayDTO.league = league
            matchDayDTO
        }
    }

    override fun update(identifier: Long, dto: MatchDayDTO): MatchDayDTO {
        val existingMatchDay: MatchDay = matchDayRepository.findById(identifier)
            .orElseThrow { ResourceNotFoundException("MatchDay with id $identifier not found") }
        leagueServiceClient.findLeagueById(dto.tournamentId)
        if (matchDayRepository.findByDateAndTournamentId(dto.date, dto.tournamentId).isPresent) {
            throw MatchDayAlreadyExistsException("MatchDay already exists in this tournament")
        }
        existingMatchDay.apply {
            this.date = dto.date
            this.tournamentId = dto.tournamentId
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
        matchDayDTO.league = leagueServiceClient.findLeagueById(matchDay.tournamentId)
        return matchDayDTO
    }
}