package com.youcode.kingsleague.models.entities

import com.youcode.kingsleague.models.enums.MatchStatus
import jakarta.persistence.*
import lombok.Builder
import java.time.LocalDateTime

@Entity
@Table(name = "matches")
@Builder
class Match (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column(nullable = false) @Temporal(TemporalType.TIMESTAMP) var date: LocalDateTime,
    var status: MatchStatus,
    @ManyToOne @JoinColumn(name = "stadium_id") val stadium: Stadium,

) {
    @OneToMany(mappedBy = "match") val matchReferees: List<MatchReferees> = listOf()
}