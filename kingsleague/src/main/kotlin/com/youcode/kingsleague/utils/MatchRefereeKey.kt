package com.youcode.kingsleague.utils

import jakarta.persistence.Column
import java.io.Serializable

class MatchRefereeKey : Serializable {
    @Column(name = "match_id") var matchID: Long = 0
    @Column(name = "referee_id") var refereeID: Long = 0
}
