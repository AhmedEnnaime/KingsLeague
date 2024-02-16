package com.youcode.kingsleague.tournamentteamsservice.services

import com.youcode.kingsleague.tournamentteamsservice.models.dto.TournamentTeamDTO

interface TournamentTeamService {

    fun registerTeamInTournament(tournamentTeamDTO: TournamentTeamDTO): TournamentTeamDTO
    fun removeTeamFromTournament(teamId: Long, tournamentId: Long)
}