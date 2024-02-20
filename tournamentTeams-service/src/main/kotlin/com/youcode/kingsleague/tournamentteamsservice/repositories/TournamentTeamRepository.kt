package com.youcode.kingsleague.tournamentteamsservice.repositories

import com.youcode.kingsleague.tournamentteamsservice.models.embeddables.TournamentTeamKey
import com.youcode.kingsleague.tournamentteamsservice.models.entities.TournamentTeam
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TournamentTeamRepository: JpaRepository<TournamentTeam, TournamentTeamKey> {
    @Query("SELECT t.id.teamId FROM TournamentTeam t WHERE t.id.tournamentId = :tournamentId")
    fun findTeamIdsByTournamentId(tournamentId: Long): List<Long>
    @Query("SELECT t.id.tournamentId FROM TournamentTeam t WHERE t.id.teamId = :teamId")
    fun findTournamentIdsByTeamId(teamId: Long): List<Long>
}