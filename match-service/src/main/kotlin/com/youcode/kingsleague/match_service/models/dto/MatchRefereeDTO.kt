package com.youcode.kingsleague.match_service.models.dto

import com.youcode.kingsleague.match_service.models.embaddables.MatchRefereeKey

data class MatchRefereeDTO (
    var id: MatchRefereeKey?,
    val match: MatchDTO?,
    val referee: RefereeDTO?,
    var notes: String
) {
    constructor() : this(null, null, null, "")
}