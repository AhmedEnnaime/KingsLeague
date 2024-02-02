package com.youcode.kingsleague.round_service.models.entities

import com.youcode.kingsleague.round_service.models.transients.Cup
import com.youcode.kingsleague.round_service.models.transients.Match
import jakarta.persistence.*
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import lombok.Builder
import java.time.LocalDate

@Entity
@Table(name = "rounds")
@Builder
data class Round (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column(nullable = false) @Temporal(value = TemporalType.DATE) @Future var date: LocalDate,
    @Column(nullable = false) @NotNull(message = "cup should not be null") var cupId: Long,
    @Column(nullable = false) @NotNull(message = "match should not be null") var matchId: Long,
    @Transient val match: Match,
    @Transient val cup: Cup
)