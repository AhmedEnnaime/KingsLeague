package com.youcode.kingsleague.round_service.repositories

import com.youcode.kingsleague.round_service.models.entities.Round
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.Optional

@Repository
interface RoundRepository: JpaRepository<Round, Long> {
    fun findByDateAndTournamentId(date: LocalDate, tournamentId: Long): Optional<Round>
}