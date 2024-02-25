package com.youcode.kingsleague.match_service.services.impl

import com.youcode.kingsleague.common.exceptions.ResourceNotFoundException
import com.youcode.kingsleague.match_service.models.dto.MatchDTO
import com.youcode.kingsleague.match_service.models.dto.RetrievalMatchDTO
import com.youcode.kingsleague.match_service.models.entities.Match
import com.youcode.kingsleague.match_service.models.transients.Team
import com.youcode.kingsleague.match_service.repositories.MatchRepository
import com.youcode.kingsleague.match_service.services.MatchService
import com.youcode.kingsleague.match_service.services.StadiumService
import com.youcode.kingsleague.match_service.services.client.MatchDayServiceClient
import com.youcode.kingsleague.match_service.services.client.TeamServiceClient
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MatchServiceImpl(private val matchRepository: MatchRepository, private val modelMapper: ModelMapper, private val teamServiceClient: TeamServiceClient, private val stadiumService: StadiumService, private val matchDayServiceClient: MatchDayServiceClient): MatchService {
    override fun save(dto: MatchDTO): MatchDTO {
        dto.createdAt = LocalDateTime.now()
        dto.updatedAt = LocalDateTime.now()
        teamServiceClient.findTeamById(dto.teamAId)
        teamServiceClient.findTeamById(dto.teamBId)
        stadiumService.findByID(dto.stadiumId)
        if (dto.matchDayId != null) {
            matchDayServiceClient.findMatchDayById(dto.matchDayId)
        }
        val matchEntity: Match = modelMapper.map(dto, Match::class.java)
        val savedMatch: Match = matchRepository.save(matchEntity)
        return modelMapper.map(savedMatch, MatchDTO::class.java)
    }

    override fun findAll(): List<RetrievalMatchDTO?>? {
        val matches: List<Match> = matchRepository.findAll()
        return matches.map { match -> modelMapper.map(match, RetrievalMatchDTO::class.java) }
    }

    override fun update(identifier: Long, dto: MatchDTO): MatchDTO {
        val existingMatch = matchRepository.findById(identifier)
            .orElseThrow { ResourceNotFoundException("Match with id $identifier not found") }
        dto.updatedAt = LocalDateTime.now()
        existingMatch.apply {
            dto.let {
                this.time = it.time
                this.matchType = it.matchType
                this.status = it.status
                this.matchDayId = it.matchDayId
                this.roundId = it.roundId
                this.teamAId = it.teamAId
                this.teamBId = it.teamBId
//                this.stadium = it.stadiumId
            }
        }
        val updatedMatch: Match = matchRepository.save(existingMatch)
        return modelMapper.map(updatedMatch, MatchDTO::class.java)
    }

    override fun delete(identifier: Long) {
        if (!matchRepository.existsById(identifier))
            throw ResourceNotFoundException("Stadium with id $identifier not found")
        matchRepository.deleteById(identifier)
    }

    override fun findByID(identifier: Long): RetrievalMatchDTO? {
        val match: Match = matchRepository.findById(identifier).orElseThrow {
            ResourceNotFoundException("Referee with id $identifier not found")
        }
        return modelMapper.map(match, RetrievalMatchDTO::class.java)
    }
}