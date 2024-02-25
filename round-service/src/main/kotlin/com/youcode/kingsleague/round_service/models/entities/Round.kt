package com.youcode.kingsleague.round_service.models.entities

import com.youcode.kingsleague.round_service.models.transients.Cup
import com.youcode.kingsleague.round_service.models.transients.Match
import jakarta.persistence.*
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import lombok.Builder
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "rounds")
@Builder
data class Round (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column(nullable = false) @Temporal(value = TemporalType.DATE) @Future var date: LocalDate,
    @Column(nullable = false) @NotNull(message = "cup should not be null") var cupId: Long,
    @CreationTimestamp @Column(nullable = true, name = "created_at") var createdAt: LocalDateTime?,
    @UpdateTimestamp @Column(nullable = true, name = "updated_at") var updatedAt: LocalDateTime?,
    @Transient val match: Match,
    @Transient val cup: Cup
) {
    @Transient val matches: List<Match> = listOf()
}
