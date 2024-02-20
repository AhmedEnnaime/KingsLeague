package com.youcode.kingsleague.match_service.models.entities

import com.youcode.kingsleague.match_service.models.embaddables.MatchRefereeKey
import jakarta.persistence.*

@Entity
@Table(name = "match_referee")
data class MatchReferee (
    @EmbeddedId var id: MatchRefereeKey?,
    @ManyToOne @MapsId("refereeId") var referee: Referee,
    @ManyToOne @MapsId("matchId") var match: Match,
    @Column var notes: String
)