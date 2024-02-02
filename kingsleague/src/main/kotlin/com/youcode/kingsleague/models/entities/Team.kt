package com.youcode.kingsleague.models.entities

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import lombok.Builder
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "teams")
@Builder
class Team (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column(nullable = false) @NotNull(message = "name can't be null") var name: String,
    @CreationTimestamp @Column(nullable = false, updatable = false, insertable = false) var created_at: LocalDateTime,
    @Column(nullable = false) @NotNull(message = "country can't be null") var country: String
) {
    @OneToMany var players: List<Player> = listOf()
}