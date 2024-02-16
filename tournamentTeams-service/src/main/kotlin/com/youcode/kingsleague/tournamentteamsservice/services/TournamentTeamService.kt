package com.youcode.kingsleague.tournamentteamsservice.services

interface TournamentTeamService {

    fun registerTeamInTournament(teamId: Long, tournamentId: Long)
    fun removeTeamFromTournament(teamId: Long, tournamentId: Long)
}