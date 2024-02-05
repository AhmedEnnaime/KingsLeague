package com.youcode.kingsleague.tournament_service.repositories

import com.youcode.kingsleague.tournament_service.models.entities.League
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LeagueRepository: JpaRepository<League, Long> {
}