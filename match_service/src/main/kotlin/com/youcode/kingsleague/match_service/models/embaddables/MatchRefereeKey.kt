package com.youcode.kingsleague.match_service.models.embaddables

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import lombok.Builder
import java.io.Serializable

@Embeddable
@Builder
data class MatchRefereeKey (
    @Column(name = "match_id") var matchId: Long,
    @Column(name = "referee_id") var refereeId: Long
): Serializable