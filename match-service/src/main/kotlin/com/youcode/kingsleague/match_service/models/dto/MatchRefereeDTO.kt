package com.youcode.kingsleague.match_service.models.dto

import com.youcode.kingsleague.match_service.models.embaddables.MatchRefereeKey
import com.youcode.kingsleague.match_service.models.enums.RefereeRole

data class MatchRefereeDTO (
    var id: MatchRefereeKey?,
    var match: RetrievalMatchDTO?,
    var referee: RefereeDTO?,
    var notes: String,
    var refereeRole: RefereeRole
) {
    constructor() : this(null, null, null, "", RefereeRole.REFEREE)
}