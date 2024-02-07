package com.youcode.kingsleague.match_service.models.entities

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import lombok.Builder
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "referees")
@Builder
class Referee (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column(nullable = false) @NotNull(message = "name can't be null") var name: String,
    @Column(nullable = false) @NotNull(message = "nationality can't be null") var nationality: String,
    @CreationTimestamp @Column(nullable = false, updatable = false, insertable = false, name = "created_at") var createdAt: LocalDateTime,
    @UpdateTimestamp @Column(nullable = false, updatable = false, insertable = false, name = "updated_at") var updatedAt: LocalDateTime,
) {
    @OneToMany(mappedBy = "referee") val matchReferee: List<MatchReferee> = listOf()
}