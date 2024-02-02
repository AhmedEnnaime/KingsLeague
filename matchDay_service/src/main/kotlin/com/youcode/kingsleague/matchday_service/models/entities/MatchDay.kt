package com.youcode.kingsleague.matchday_service.models.entities

import com.youcode.kingsleague.matchday_service.models.transients.League
import com.youcode.kingsleague.matchday_service.models.transients.Match
import jakarta.persistence.*
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "matchDays")
data class MatchDay (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column(nullable = false) @Temporal(value = TemporalType.DATE) @Future var date: LocalDate,
    @Column(nullable = false) @NotNull(message = "league should not be null") var leagueId: Long,
    @Column(nullable = false) @NotNull(message = "match should not be null") var matchId: Long,
    @CreationTimestamp @Column(nullable = false, updatable = false, insertable = false, name = "created_at") var createdAt: LocalDateTime,
    @UpdateTimestamp @Column(nullable = false, updatable = false, insertable = false, name = "updated_at") var updatedAt: LocalDateTime,
    @Transient val league: League,
    @Transient val match: Match,
)
