package com.youcode.kingsleague.match_service.services

import com.youcode.kingsleague.match_service.models.dto.MatchRefereeDTO

interface MatchRefereeService {
    fun assignRefereeToMatch(matchRefereeDTO: MatchRefereeDTO): MatchRefereeDTO
}