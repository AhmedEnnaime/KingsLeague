package com.youcode.kingsleague.match_service.models.entities

import com.youcode.kingsleague.match_service.models.transients.Team
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "results")
class Result (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column(nullable = false) @NotBlank(message = "score should not be empty") var score: String,
    @Column(nullable = true, name = "team_id") var teamId: Long?,
    @OneToOne(fetch = FetchType.EAGER) @JoinColumn(name = "match_id", referencedColumnName = "id") var match: Match,
    @Transient var winner: Team?,
    @CreationTimestamp @Column(nullable = true, name = "created_at") var createdAt: LocalDateTime?,
    @UpdateTimestamp @Column(nullable = true, name = "updated_at") var updatedAt: LocalDateTime?,
    )