package com.youcode.kingsleague.tournamentservice.services

import com.youcode.kingsleague.tournamentservice.models.dto.CupDTO
import com.youcode.kingsleague.tournamentservice.models.dto.LeagueDTO
import com.youcode.kingsleague.tournamentservice.models.dto.TournamentDTO

interface TournamentService {

    fun createLeague(leagueDTO: LeagueDTO): LeagueDTO
    fun createCup(cupDTO: CupDTO): CupDTO
    fun findAll(): List<TournamentDTO>
    fun findById(id: Long): TournamentDTO
    fun findLeagueById(id: Long): LeagueDTO
    fun findCupById(id: Long): CupDTO
    fun update(id: Long, tournamentDTO: TournamentDTO): TournamentDTO
    fun delete(id: Long)
    fun findLeagues(): List<LeagueDTO>
    fun findCups(): List<CupDTO>
}