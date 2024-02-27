package com.youcode.kingsleague.matchday_service.repositories

import com.youcode.kingsleague.matchday_service.models.entities.MatchDay
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.Optional

@Repository
interface MatchDayRepository: JpaRepository<MatchDay, Long> {
    fun findByDateAndTournamentId(date: LocalDate, tournamentId: Long): Optional<MatchDay>
}