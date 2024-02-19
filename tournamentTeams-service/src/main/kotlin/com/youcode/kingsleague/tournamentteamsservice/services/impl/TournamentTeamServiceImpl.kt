package com.youcode.kingsleague.tournamentteamsservice.services.impl

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

@Service
class TournamentTeamServiceImpl(private val tournamentTeamRepository: TournamentTeamRepository, private val tournamentServiceClient: TournamentServiceClient, private val teamServiceClient: TeamServiceClient, private val modelMapper: ModelMapper): TournamentTeamService {
    override fun registerTeamInTournament(tournamentTeamDTO: TournamentTeamDTO): TournamentTeamDTO {
        val tournament: Tournament = tournamentServiceClient.findTournamentById(tournamentTeamDTO.tournament?.id!!)
        val team: Team = teamServiceClient.findTeamById(tournamentTeamDTO.team?.id!!)
        tournamentTeamDTO.id = TournamentTeamKey(teamId = team.id!!, tournamentId = tournament.id!!)
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
        TODO("Not yet implemented")
    }

}