package com.youcode.kingsleague.team_service.models.entities

import jakarta.persistence.*
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import lombok.Builder
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "players")
@Builder
data class Player (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column(nullable = false) @NotNull(message = "first name can't be null") var firstName: String,
    @Column(nullable = false) @NotNull(message = "last name can't be null") var lastName: String,
    @Column(nullable = true) @Min(value = 50) var weight: Double,
    @Column(nullable = true) @Min(value = 0) var height: Double,
    @Column(nullable = false) @Temporal(TemporalType.DATE) var birthday: LocalDate,
    @Column(nullable = false) @NotNull(message = "nationality can't be null") var nationality: String,
    @CreationTimestamp @Column(nullable = false, updatable = false, insertable = false, name = "created_at") var createdAt: LocalDateTime,
    @UpdateTimestamp @Column(nullable = false, updatable = false, insertable = false, name = "updated_at") var updatedAt: LocalDateTime,
) {
    @OneToMany(mappedBy = "player") var teams: List<TeamPlayer> = listOf()
}
