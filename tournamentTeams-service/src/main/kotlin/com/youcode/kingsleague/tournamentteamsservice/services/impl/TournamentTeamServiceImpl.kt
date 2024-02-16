package com.youcode.kingsleague.tournamentteamsservice.services.impl

import com.youcode.kingsleague.tournamentteamsservice.repositories.TournamentTeamRepository
import com.youcode.kingsleague.tournamentteamsservice.services.TournamentTeamService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class TournamentTeamServiceImpl(private val tournamentTeamRepository: TournamentTeamRepository, private val modelMapper: ModelMapper): TournamentTeamService {
    override fun registerTeamInTournament(teamId: Long, tournamentId: Long) {
        TODO("Not yet implemented")
    }

    override fun removeTeamFromTournament(teamId: Long, tournamentId: Long) {
        TODO("Not yet implemented")
    }

}