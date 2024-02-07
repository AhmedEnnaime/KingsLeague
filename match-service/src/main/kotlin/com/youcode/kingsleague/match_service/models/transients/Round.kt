package com.youcode.kingsleague.match_service.models.transients

import com.youcode.kingsleague.match_service.models.dto.MatchDTO
import lombok.Builder
import java.time.LocalDate

@Builder
data class Round (
    var id: Long,
    var date: LocalDate,
    var match: MatchDTO,
    var cup: Cup
)