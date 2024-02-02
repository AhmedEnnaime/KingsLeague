package com.youcode.kingsleague.match_service.models.entities

import com.youcode.kingsleague.match_service.models.enums.MatchStatus
import com.youcode.kingsleague.match_service.models.transients.Team
import jakarta.persistence.*
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import lombok.Builder
import java.time.LocalDateTime

@Entity
@Table(name = "matchs")
@Builder
data class Match (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column(nullable = false) @Temporal(value = TemporalType.TIMESTAMP) @Future var date: LocalDateTime,
    @Column(nullable = false) @Enumerated(EnumType.STRING) var status: MatchStatus = MatchStatus.SCHEDULED,
    @ManyToOne @JoinColumn(name = "stadium_id") val stadium: Stadium,
    @OneToOne(mappedBy = "match") val result: Result,
    @Column(nullable = false, name = "teamA_id") @NotNull(message = "team A should not be null") var teamAId: Long,
    @Column(nullable = false, name = "teamA_id") @NotNull(message = "team B should not be null") var teamBId: Long,
    @Transient val teamA: Team,
    @Transient val teamB: Team
){
    @OneToMany(mappedBy = "match") val matchReferee: List<MatchReferee> = listOf()
}