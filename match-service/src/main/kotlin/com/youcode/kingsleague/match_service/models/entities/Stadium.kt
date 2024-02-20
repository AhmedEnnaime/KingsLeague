package com.youcode.kingsleague.match_service.models.entities

import jakarta.persistence.*
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import lombok.Builder
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "stadiums")
@Builder
data class Stadium (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column(nullable = false) @NotNull(message = "name can't be null") var name: String,
    @Column(nullable = false) @NotNull(message = "location can't be null") var location: String,
    @Column(nullable = false) @Min(value = 500, message = "capacity of a stadium should not be less than 500") var capacity: Int,
    @CreationTimestamp @Column(nullable = true, name = "created_at") var createdAt: LocalDateTime?,
    @UpdateTimestamp @Column(nullable = true, name = "updated_at") var updatedAt: LocalDateTime?,
) {
    @OneToMany(mappedBy = "stadium") var matches: List<Match> = listOf()
}