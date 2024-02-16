package com.youcode.kingsleague.tournamentteamsservice.services.impl

import com.youcode.kingsleague.tournamentteamsservice.models.transients.Tournament
import com.youcode.kingsleague.tournamentteamsservice.repositories.TournamentTeamRepository
import com.youcode.kingsleague.tournamentteamsservice.services.TournamentTeamService
import com.youcode.kingsleague.tournamentteamsservice.services.client.TournamentServiceClient
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class TournamentTeamServiceImpl(private val tournamentTeamRepository: TournamentTeamRepository, private val tournamentServiceClient: TournamentServiceClient, private val modelMapper: ModelMapper): TournamentTeamService {
    override fun registerTeamInTournament(teamId: Long, tournamentId: Long) {
        val tournament: Tournament = tournamentServiceClient.findTournamentById(tournamentId)
        TODO("Not yet implemented")
    }

    override fun removeTeamFromTournament(teamId: Long, tournamentId: Long) {
        TODO("Not yet implemented")
    }

}