package com.youcode.kingsleague.tournamentservice.repositories

import com.youcode.kingsleague.tournamentservice.models.entities.Cup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CupRepository: JpaRepository<Cup, Long> {
}