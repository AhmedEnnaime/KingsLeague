package com.youcode.kingsleague.matchday_service.services

import com.youcode.kingsleague.common.services.GlobalService
import com.youcode.kingsleague.matchday_service.models.dto.MatchDayDTO

interface MatchDayService: GlobalService<MatchDayDTO, Long> {
    fun findByTournamentId(tournamentId: Long): List<MatchDayDTO>
}