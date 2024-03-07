package com.youcode.kingsleague.tournamentteamsservice.services.impl

import com.youcode.kingsleague.common.exceptions.MaxTeamsReachedException
import com.youcode.kingsleague.common.exceptions.RegistrationTimeExpiredException
import com.youcode.kingsleague.common.exceptions.ResourceNotFoundException
import com.youcode.kingsleague.tournamentteamsservice.models.dto.TournamentTeamDTO
import com.youcode.kingsleague.tournamentteamsservice.models.embeddables.TournamentTeamKey
import com.youcode.kingsleague.tournamentteamsservice.models.entities.TournamentTeam
import com.youcode.kingsleague.tournamentteamsservice.models.transients.Team
import com.youcode.kingsleague.tournamentteamsservice.models.transients.Tournament
import com.youcode.kingsleague.tournamentteamsservice.repositories.TournamentTeamRepository
import com.youcode.kingsleague.tournamentteamsservice.services.TournamentTeamService
import com.youcode.kingsleague.tournamentteamsservice.services.client.TeamServiceClient
import com.youcode.kingsleague.tournamentteamsservice.services.client.TournamentServiceClient
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class TournamentTeamServiceImpl(private val tournamentTeamRepository: TournamentTeamRepository, private val tournamentServiceClient: TournamentServiceClient, private val teamServiceClient: TeamServiceClient, private val modelMapper: ModelMapper): TournamentTeamService {
    override fun registerTeamInTournament(tournamentTeamDTO: TournamentTeamDTO): TournamentTeamDTO {
        tournamentTeamDTO.createdAt = LocalDateTime.now()
        tournamentTeamDTO.updatedAt = LocalDateTime.now()
        val tournament: Tournament = tournamentServiceClient.findTournamentById(tournamentTeamDTO.tournament?.id!!)
        val team: Team = teamServiceClient.findTeamById(tournamentTeamDTO.team?.id!!)
        tournamentTeamDTO.id = TournamentTeamKey(teamId = team.id!!, tournamentId = tournament.id!!)
        if (tournament.debutDate <= LocalDate.now()) {
            throw RegistrationTimeExpiredException("Registration date of this tournament has expired in this date ${tournament.debutDate}")
        }
        if (tournament.teamsNum <= tournamentTeamRepository.findTeamIdsByTournamentId(tournament.id!!).size) {
            throw MaxTeamsReachedException("This tournament is already full and cannot surpass this number of registered teams: ${tournament.teamsNum}")
        }
        if (tournament.tournamentType == "LEAGUE")
            tournamentTeamDTO.points = 0
        tournamentTeamDTO.team = team
        tournamentTeamDTO.tournament = tournament
        val tournamentTeamEntity: TournamentTeam = modelMapper.map(tournamentTeamDTO, TournamentTeam::class.java)
        val savedTournamentTeam: TournamentTeam = tournamentTeamRepository.save(tournamentTeamEntity)
        return modelMapper.map(savedTournamentTeam, TournamentTeamDTO::class.java)
    }

    override fun removeTeamFromTournament(teamId: Long, tournamentId: Long) {
        tournamentServiceClient.findTournamentById(tournamentId)
        teamServiceClient.findTeamById(teamId)
        val tournamentTeamKey = TournamentTeamKey(teamId = teamId, tournamentId = tournamentId)
        val tournamentTeam: TournamentTeam = tournamentTeamRepository.findById(tournamentTeamKey).orElseThrow{ResourceNotFoundException("Tournament Team with id $tournamentTeamKey not found")}
        tournamentTeamRepository.delete(tournamentTeam)
    }

    override fun findTournamentTeams(tournamentId: Long): List<Team> {
        val teamIds: List<Long> = tournamentTeamRepository.findTeamIdsByTournamentId(tournamentId)
        val teams: List<Team> = teamIds.map { teamId ->
            teamServiceClient.findTeamById(teamId)
        }
        return teams
    }

    override fun findTeamTournaments(teamId: Long): List<Tournament> {
        val tournamentIds: List<Long> = tournamentTeamRepository.findTournamentIdsByTeamId(teamId)
        val tournaments: List<Tournament> = tournamentIds.map { tournamentId ->
            tournamentServiceClient.findTournamentById(tournamentId)
        }
        return tournaments
    }

    override fun findTournamentTeamById(tournamentId: Long, teamId: Long): TournamentTeamDTO {
        val tournamentTeamKey = TournamentTeamKey(teamId, tournamentId)
        val tournamentTeam: TournamentTeam = tournamentTeamRepository.findById(tournamentTeamKey)
            .orElseThrow { ResourceNotFoundException("TournamentTeam with id $tournamentTeamKey not found") }
        val tournamentTeamDTO: TournamentTeamDTO = modelMapper.map(tournamentTeam, TournamentTeamDTO::class.java)
        tournamentTeamDTO.team = teamServiceClient.findTeamById(teamId)
        tournamentTeamDTO.tournament = tournamentServiceClient.findTournamentById(tournamentId)
        return tournamentTeamDTO
    }

    override fun updateTeamPointsInTournament(tournamentTeamDTO: TournamentTeamDTO) {
        val tournamentTeam: TournamentTeam = tournamentTeamRepository.findById(tournamentTeamDTO.id!!).get()
        tournamentTeam.points = tournamentTeam.points?.plus(tournamentTeamDTO.points!!)
    }

    override fun findAllTournamentTeams(): List<TournamentTeamDTO> {
        val tournamentTeams: List<TournamentTeam> = tournamentTeamRepository.findAll()
        return tournamentTeams.map { tournamentTeam ->
            val team: Team = teamServiceClient.findTeamById(tournamentTeam.id?.teamId!!)
            val tournament: Tournament = tournamentServiceClient.findTournamentById(tournamentTeam.id?.tournamentId!!)
            val tournamentTeamDTO: TournamentTeamDTO = modelMapper.map(tournamentTeam, TournamentTeamDTO::class.java)
            tournamentTeamDTO.team = team
            tournamentTeamDTO.tournament = tournament
            tournamentTeamDTO
        }
    }

}