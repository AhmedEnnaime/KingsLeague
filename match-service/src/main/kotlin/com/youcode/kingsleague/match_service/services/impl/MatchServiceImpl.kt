package com.youcode.kingsleague.match_service.services.impl

import com.youcode.kingsleague.common.exceptions.ResourceNotFoundException
import com.youcode.kingsleague.match_service.models.dto.MatchDTO
import com.youcode.kingsleague.match_service.models.dto.RetrievalMatchDTO
import com.youcode.kingsleague.match_service.models.entities.Match
import com.youcode.kingsleague.match_service.models.enums.MatchType
import com.youcode.kingsleague.match_service.models.transients.MatchDay
import com.youcode.kingsleague.match_service.models.transients.Team
import com.youcode.kingsleague.match_service.repositories.MatchRepository
import com.youcode.kingsleague.match_service.services.MatchService
import com.youcode.kingsleague.match_service.services.StadiumService
import com.youcode.kingsleague.match_service.services.client.MatchDayServiceClient
import com.youcode.kingsleague.match_service.services.client.RoundServiceClient
import com.youcode.kingsleague.match_service.services.client.TeamServiceClient
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MatchServiceImpl(private val matchRepository: MatchRepository, private val modelMapper: ModelMapper, private val teamServiceClient: TeamServiceClient, private val stadiumService: StadiumService, private val matchDayServiceClient: MatchDayServiceClient, private val roundServiceClient: RoundServiceClient): MatchService {
    override fun save(dto: MatchDTO): MatchDTO {
        dto.createdAt = LocalDateTime.now()
        dto.updatedAt = LocalDateTime.now()
        teamServiceClient.findTeamById(dto.opponentAId)
        teamServiceClient.findTeamById(dto.opponentBId)
        stadiumService.findByID(dto.stadiumId!!)
        if (dto.matchDayId != null) {
            matchDayServiceClient.findMatchDayById(dto.matchDayId)
        }else if (dto.roundId != null) {
            roundServiceClient.findRoundById(dto.roundId)
        }
        val matchEntity: Match = modelMapper.map(dto, Match::class.java)
        val savedMatch: Match = matchRepository.save(matchEntity)
        return modelMapper.map(savedMatch, MatchDTO::class.java)
    }

    override fun findAll(): List<RetrievalMatchDTO?>? {
        println("INSIDE FUNC")
        val matches: List<Match> = matchRepository.findAll()
        println("MATCHES LIST $matches")
        return matches.map { match ->
            println("OPPONENT ID ${match.opponentAId}")
            val teamA: Team = teamServiceClient.findTeamById(match.opponentAId)
            val teamB: Team = teamServiceClient.findTeamById(match.opponentBId)
            val matchDay: MatchDay? = match.matchDayId?.let { matchDayServiceClient.findMatchDayById(it) }
//            val result: ResultDTO = resultService.findByID()
//            val stadium: StadiumDTO = stadiumService.findByID(match.stadium.id)!!

            val matchDTO: RetrievalMatchDTO = modelMapper.map(match, RetrievalMatchDTO::class.java)
            matchDTO.matchDay = matchDay
            matchDTO.teamA = teamA
            matchDTO.teamB = teamB
//            matchDTO.stadium = stadium
            matchDTO
        }
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
                this.opponentBId = it.opponentAId
                this.opponentBId = it.opponentBId
//                this.stadium = it.stadiumId
            }
        }
        val updatedMatch: Match = matchRepository.save(existingMatch)
        return modelMapper.map(updatedMatch, MatchDTO::class.java)
    }

    override fun findByMatchDayId(matchDayId: Long): List<MatchDTO> {
        val matches: List<Match> = matchRepository.findByMatchDayId(matchDayId)
        return matches.map { match ->
            val teamA: Team = teamServiceClient.findTeamById(match.opponentAId)
            val teamB: Team = teamServiceClient.findTeamById(match.opponentBId)
//            val result: Result = resultRepository.findByMatchId(match.id)
            val matchDTO: MatchDTO = modelMapper.map(match, MatchDTO::class.java)
            matchDTO.teamA = teamA
            matchDTO.teamB = teamB
//            matchDTO.result = modelMapper.map(result, ResultDTO::class.java)
            matchDTO
        }
    }

    override fun findByRoundId(roundId: Long): List<MatchDTO> {
        val matches: List<Match> = matchRepository.findByRoundId(roundId)
        return matches.map { match ->
            val teamA: Team = teamServiceClient.findTeamById(match.opponentAId)
            val teamB: Team = teamServiceClient.findTeamById(match.opponentBId)
//            val result: Result = resultRepository.findByMatchId(match.id)
            val matchDTO: MatchDTO = modelMapper.map(match, MatchDTO::class.java)
            matchDTO.teamA = teamA
            matchDTO.teamB = teamB
//            matchDTO.result = modelMapper.map(result, ResultDTO::class.java)
            matchDTO
        }
    }

    override fun delete(identifier: Long) {
        if (!matchRepository.existsById(identifier))
            throw ResourceNotFoundException("Stadium with id $identifier not found")
        matchRepository.deleteById(identifier)
    }

    override fun findByID(identifier: Long): RetrievalMatchDTO? {
        val match: Match = matchRepository.findById(identifier).orElseThrow {
            ResourceNotFoundException("Match with id $identifier not found")
        }
        val matchDTO: RetrievalMatchDTO = modelMapper.map(match, RetrievalMatchDTO::class.java)
        matchDTO.teamA = teamServiceClient.findTeamById(match.opponentAId)
        matchDTO.teamB = teamServiceClient.findTeamById(match.opponentBId)
        if (matchDTO.matchType == MatchType.LEAGUE) {
            matchDTO.matchDay =  matchDayServiceClient.findMatchDayById(matchDayId = match.matchDayId!!)
        }else {
            matchDTO.round = match.roundId?.let { roundServiceClient.findRoundById(it) }
        }
//        matchDTO.stadium = stadiumService.findByID(match.stadium.id)
        return matchDTO
    }
}