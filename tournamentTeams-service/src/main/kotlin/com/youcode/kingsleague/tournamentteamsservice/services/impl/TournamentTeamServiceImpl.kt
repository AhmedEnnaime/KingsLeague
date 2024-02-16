package com.youcode.kingsleague.tournamentteamsservice.services.impl

import com.youcode.kingsleague.common.exceptions.ResourceNotFoundException
import com.youcode.kingsleague.tournamentteamsservice.models.dto.TournamentTeamDTO
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
        val tournament: Tournament = tournamentServiceClient.findTournamentById(tournamentTeamDTO.tournament.id!!)
        val team: Team = teamServiceClient.findTeamById(tournamentTeamDTO.team.id!!)
        if (tournament != null && team != null) {
            val tournamentTeamEntity: TournamentTeam = modelMapper.map(tournamentTeamDTO, TournamentTeam::class.java)
            val savedTournamentTeam: TournamentTeam = tournamentTeamRepository.save(tournamentTeamEntity)
            return modelMapper.map(savedTournamentTeam, TournamentTeamDTO::class.java)
        }else {
            throw ResourceNotFoundException("Team or Tournament are not valid")
        }
    }

    override fun removeTeamFromTournament(teamId: Long, tournamentId: Long) {
        TODO("Not yet implemented")
    }

}