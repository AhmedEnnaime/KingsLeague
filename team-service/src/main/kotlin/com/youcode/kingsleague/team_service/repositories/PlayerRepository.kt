package com.youcode.kingsleague.team_service.repositories

import com.youcode.kingsleague.team_service.models.entities.Player
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlayerRepository: JpaRepository<Player, Long> {
}