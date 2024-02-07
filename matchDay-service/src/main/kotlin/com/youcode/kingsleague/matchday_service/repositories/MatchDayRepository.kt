package com.youcode.kingsleague.matchday_service.repositories

import com.youcode.kingsleague.matchday_service.models.entities.MatchDay
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MatchDayRepository: JpaRepository<MatchDay, Long> {
}