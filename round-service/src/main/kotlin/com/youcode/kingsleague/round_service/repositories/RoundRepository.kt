package com.youcode.kingsleague.round_service.repositories

import com.youcode.kingsleague.round_service.models.entities.Round
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoundRepository: JpaRepository<Round, Long> {
}