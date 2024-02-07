package com.youcode.kingsleague.team_service.models.entities

import com.youcode.kingsleague.team_service.models.embeddables.TeamPlayerKey
import jakarta.persistence.*
import lombok.Builder
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "team_player")
@Builder
data class TeamPlayer (
    @EmbeddedId var id: TeamPlayerKey,
    @ManyToOne @MapsId("playedId") var player: Player,
    @ManyToOne @MapsId("teamId") var team: Team,
    @CreationTimestamp @Column(nullable = false, updatable = false, insertable = false, name = "joined_at") var joinedAt: LocalDateTime,
    @UpdateTimestamp @Column(nullable = false, updatable = false, insertable = false, name = "updated_at") var updatedAt: LocalDateTime,
)