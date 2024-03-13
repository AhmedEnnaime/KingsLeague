package com.youcode.kingsleague.match_service.repositories

import com.youcode.kingsleague.match_service.models.entities.Match
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MatchRepository: JpaRepository<Match, Long> {
    fun findByMatchDayId(matchDayId: Long): List<Match>
    fun findByRoundId(roundId: Long): List<Match>
}