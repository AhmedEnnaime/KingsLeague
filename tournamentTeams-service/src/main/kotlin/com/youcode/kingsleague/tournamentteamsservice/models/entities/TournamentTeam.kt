package com.youcode.kingsleague.tournamentteamsservice.models.entities

import com.youcode.kingsleague.tournamentteamsservice.models.embeddables.TournamentTeamKey
import com.youcode.kingsleague.tournamentteamsservice.models.transients.Team
import com.youcode.kingsleague.tournamentteamsservice.models.transients.Tournament
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "tournament_teams")
data class TournamentTeam (
    @EmbeddedId var id: TournamentTeamKey?,
    @Transient val team: Team?,
    @Transient val tournament: Tournament?,
    @Column(nullable = true ,columnDefinition = "INT DEFAULT 0") var points: Int?,
    @CreationTimestamp @Column(nullable = true, name = "created_at") var createdAt: LocalDateTime?,
    @UpdateTimestamp @Column(nullable = true, name = "updated_at") var updatedAt: LocalDateTime?,
)