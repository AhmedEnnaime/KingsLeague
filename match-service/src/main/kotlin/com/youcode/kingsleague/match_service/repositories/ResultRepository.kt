package com.youcode.kingsleague.match_service.repositories

import com.youcode.kingsleague.match_service.models.entities.Result
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ResultRepository: JpaRepository<Result, Long> {
    fun findByMatchId(matchId: Long): Result
}