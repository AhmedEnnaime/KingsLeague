package com.youcode.kingsleague.match_service.services

import com.youcode.kingsleague.common.services.GlobalService
import com.youcode.kingsleague.match_service.models.dto.ResultDTO

interface ResultService: GlobalService<ResultDTO, Long> {
    fun findResultByMatchId(matchId: Long): List<ResultDTO>
}