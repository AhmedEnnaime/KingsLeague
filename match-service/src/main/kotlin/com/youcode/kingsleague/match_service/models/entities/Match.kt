package com.youcode.kingsleague.match_service.models.entities

import com.youcode.kingsleague.match_service.models.enums.MatchStatus
import com.youcode.kingsleague.match_service.models.enums.MatchType
import com.youcode.kingsleague.match_service.models.transients.MatchDay
import com.youcode.kingsleague.match_service.models.transients.Round
import com.youcode.kingsleague.match_service.models.transients.Team
import jakarta.persistence.*
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import lombok.Builder
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.time.LocalTime

@Entity
@Table(name = "matchs")
@Builder
data class Match (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column(nullable = false) @Temporal(value = TemporalType.TIME) @Future var time: LocalTime,
    @Column(nullable = false) @Enumerated(EnumType.STRING) var status: MatchStatus = MatchStatus.SCHEDULED,
    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "stadium_id") val stadium: Stadium,
    @OneToOne(mappedBy = "match") val result: Result,
    @Column(nullable = false, name = "teamA_id") @NotNull(message = "team A should not be null") var teamAId: Long,
    @Column(nullable = false, name = "teamB_id") @NotNull(message = "team B should not be null") var teamBId: Long,
    @Column(nullable = true, name = "matchDay_id") var matchDayId: Long?,
    @Column(nullable = true, name = "round_id") var roundId: Long?,
    @Column(nullable = false) @Enumerated(EnumType.STRING) var matchType: MatchType,
    @Transient val teamA: Team,
    @Transient val teamB: Team,
    @Transient val matchDay: MatchDay,
    @Transient val round: Round,
    @CreationTimestamp @Column(nullable = true, name = "created_at") var createdAt: LocalDateTime?,
    @UpdateTimestamp @Column(nullable = true, name = "updated_at") var updatedAt: LocalDateTime?,
){
    @OneToMany(mappedBy = "match") val matchReferee: List<MatchReferee> = listOf()
}