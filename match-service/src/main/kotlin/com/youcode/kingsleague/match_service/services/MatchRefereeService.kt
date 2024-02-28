package com.youcode.kingsleague.match_service.services

import com.youcode.kingsleague.match_service.models.dto.MatchDTO
import com.youcode.kingsleague.match_service.models.dto.MatchRefereeDTO
import com.youcode.kingsleague.match_service.models.dto.RefereeDTO

interface MatchRefereeService {
    fun assignRefereeToMatch(matchRefereeDTO: MatchRefereeDTO): MatchRefereeDTO
    fun findMatchReferees(matchId: Long): List<RefereeDTO>
    fun findRefereeMatches(refereeId: Long): List<MatchDTO>
}