package com.youcode.kingsleague.models.entities

import com.youcode.kingsleague.utils.MatchRefereeKey
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId

@Entity
class MatchReferees (

    @EmbeddedId
    var id: MatchRefereeKey,
    @ManyToOne @MapsId("refereeID") @JoinColumn(name = "referee_id") var referee: Referee,
    @ManyToOne @MapsId("matchID") @JoinColumn(name = "match_id") var match: Match

)