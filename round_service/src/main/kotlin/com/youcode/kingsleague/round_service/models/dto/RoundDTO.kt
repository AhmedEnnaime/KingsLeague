package com.youcode.kingsleague.round_service.models.dto

import com.youcode.kingsleague.round_service.models.transients.Cup
import com.youcode.kingsleague.round_service.models.transients.Match
import lombok.Builder
import java.time.LocalDate

@Builder
data class RoundDTO (
    var id: Long,
    var date: LocalDate,
    var match: Match,
    var cup: Cup
)