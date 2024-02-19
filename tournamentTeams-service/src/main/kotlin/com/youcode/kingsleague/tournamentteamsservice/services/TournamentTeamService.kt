package com.youcode.kingsleague.tournamentteamsservice.services

import com.youcode.kingsleague.tournamentteamsservice.models.dto.TournamentTeamDTO
import com.youcode.kingsleague.tournamentteamsservice.models.transients.Team
import com.youcode.kingsleague.tournamentteamsservice.models.transients.Tournament

interface TournamentTeamService {

    fun registerTeamInTournament(tournamentTeamDTO: TournamentTeamDTO): TournamentTeamDTO
    fun removeTeamFromTournament(teamId: Long, tournamentId: Long)
    fun findTournamentTeams(tournamentId: Long): List<Team>
    fun findTeamTournaments(teamId: Long): List<Tournament>
}