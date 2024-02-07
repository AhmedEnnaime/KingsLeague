package com.youcode.kingsleague.match_service.repositories

import com.youcode.kingsleague.match_service.models.entities.Referee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RefereeRepository: JpaRepository<Referee, Long> {
}