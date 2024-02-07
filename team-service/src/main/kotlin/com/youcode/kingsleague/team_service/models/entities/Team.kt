package com.youcode.kingsleague.team_service.models.entities

import com.youcode.kingsleague.team_service.models.transients.Match
import com.youcode.kingsleague.team_service.models.transients.Tournament
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import lombok.Builder
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "teams")
@Builder
data class Team (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column(nullable = false) @NotNull(message = "name can't be null") var name: String,
    @Column(nullable = false) @NotNull(message = "country can't be null") var country: String,
    @Column(nullable = true, name = "tournament_id") var tournamentId: Long,
    @CreationTimestamp @Column(nullable = false, updatable = false, insertable = false, name = "created_at") var createdAt: LocalDateTime,
    @UpdateTimestamp @Column(nullable = false, updatable = false, insertable = false, name = "updated_at") var updatedAt: LocalDateTime,
    @Transient var matches: List<Match>,
    @Transient val tournament: Tournament
){
    @OneToMany(mappedBy = "team") var players: List<TeamPlayer> = listOf()
}