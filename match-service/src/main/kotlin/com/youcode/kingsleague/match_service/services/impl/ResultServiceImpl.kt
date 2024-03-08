package com.youcode.kingsleague.match_service.services.impl

import com.youcode.kingsleague.common.exceptions.ResourceNotFoundException
import com.youcode.kingsleague.match_service.models.dto.ResultDTO
import com.youcode.kingsleague.match_service.models.dto.RetrievalMatchDTO
import com.youcode.kingsleague.match_service.repositories.ResultRepository
import com.youcode.kingsleague.match_service.services.ResultService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import com.youcode.kingsleague.match_service.models.entities.Result
import com.youcode.kingsleague.match_service.models.enums.MatchType
import com.youcode.kingsleague.match_service.models.transients.Team
import com.youcode.kingsleague.match_service.models.transients.TournamentTeam
import com.youcode.kingsleague.match_service.services.MatchService
import com.youcode.kingsleague.match_service.services.client.TeamServiceClient
import com.youcode.kingsleague.match_service.services.client.TournamentTeamServiceClient

@Service
class ResultServiceImpl(private val modelMapper: ModelMapper, private val resultRepository: ResultRepository, private val tournamentTeamServiceClient: TournamentTeamServiceClient, private val matchService: MatchService, private val teamServiceClient: TeamServiceClient): ResultService {
    override fun save(dto: ResultDTO): ResultDTO {
        dto.createdAt = LocalDateTime.now()
        dto.updatedAt = LocalDateTime.now()
        val resultEntity: Result = modelMapper.map(dto, Result::class.java)
        val match: RetrievalMatchDTO = matchService.findByID(dto.matchId)!!
        println("Match type is ${match.matchType}")
        println("Match team id is ${dto.teamId}")
        println("Match team A id is ${match.teamA?.id}")
        println("Match team B id is ${match.teamB?.id}")
        if (match.matchType == MatchType.LEAGUE && dto.teamId != null) {
            val tournamentTeam: TournamentTeam = tournamentTeamServiceClient.findTournamentTeamById(match.matchDay?.league?.id!!, dto.teamId!!)
            tournamentTeam.points = tournamentTeam.points?.plus(3)
            tournamentTeamServiceClient.updateTeamTournamentPoints(tournamentTeam)
        }else if (match.matchType == MatchType.LEAGUE && dto.teamId == null) {
            println("INSIDE IF")
            println("MatchDay is $match")
            println("League is ${match.matchDay?.league}")
            val tournamentTeamA: TournamentTeam = tournamentTeamServiceClient.findTournamentTeamById(match.matchDay?.league?.id!!, match.teamA?.id!!)
            val tournamentTeamB: TournamentTeam = tournamentTeamServiceClient.findTournamentTeamById(match.matchDay?.league?.id!!, match.teamB?.id!!)
            println("Tournament Team A points ${tournamentTeamA.points}")
            println("Tournament Team B points ${tournamentTeamA.points}")
            tournamentTeamA.points = tournamentTeamA.points?.plus(1)
            tournamentTeamB.points = tournamentTeamB.points?.plus(1)
            tournamentTeamServiceClient.updateTeamTournamentPoints(tournamentTeamA)
            tournamentTeamServiceClient.updateTeamTournamentPoints(tournamentTeamB)
        }
        val savedResult: Result = resultRepository.save(resultEntity)
        return modelMapper.map(savedResult, ResultDTO::class.java)
    }

    override fun getAll(): List<ResultDTO?>? {
        val results: List<Result> = resultRepository.findAll()
        return results.map { result ->
            val match: RetrievalMatchDTO = matchService.findByID(result.match.id)!!
            val winner: Team = teamServiceClient.findTeamById(result.teamId!!)
            val resultDTO: ResultDTO = modelMapper.map(result, ResultDTO::class.java)
            resultDTO.winner = winner
//            resultDTO.matchDTO = match
            resultDTO
        }
    }

    override fun update(identifier: Long, dto: ResultDTO): ResultDTO {
        val existingResult: Result = resultRepository.findById(identifier)
            .orElseThrow { ResourceNotFoundException("Result with id $identifier not found") }
        dto.updatedAt = LocalDateTime.now()
        existingResult.apply {
            dto.let {
                this.score = it.score
                this.teamId = it.teamId!!
                this.winner = it.winner!!
//                this.match = modelMapper.map(it.matchDTO, Match::class.java)
            }
        }
        val updatedResult: Result = resultRepository.save(existingResult)
        return modelMapper.map(updatedResult, ResultDTO::class.java)
    }

    override fun delete(identifier: Long) {
        if (!resultRepository.existsById(identifier))
            throw ResourceNotFoundException("Stadium with id $identifier not found")
        resultRepository.deleteById(identifier)
    }

    override fun findByID(identifier: Long): ResultDTO? {
        val result: Result = resultRepository.findById(identifier).orElseThrow {
            ResourceNotFoundException("Result with id $identifier not found")
        }
        return modelMapper.map(result, ResultDTO::class.java)
    }
}