package com.youcode.kingsleague.match_service.services

import com.youcode.kingsleague.common.services.GlobalService
import com.youcode.kingsleague.match_service.models.dto.MatchDTO

interface MatchService: GlobalService<MatchDTO, Long> {
}