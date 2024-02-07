package com.youcode.kingsleague.tournamentservice.repositories

import com.youcode.kingsleague.tournamentservice.models.entities.League
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LeagueRepository: JpaRepository<League, Long> {
}