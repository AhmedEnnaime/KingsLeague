package com.youcode.kingsleague.tournamentservice.repositories

import com.youcode.kingsleague.tournamentservice.models.entities.League
import com.youcode.kingsleague.tournamentservice.models.entities.Tournament
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TournamentRepository: JpaRepository<Tournament, Long> {
}