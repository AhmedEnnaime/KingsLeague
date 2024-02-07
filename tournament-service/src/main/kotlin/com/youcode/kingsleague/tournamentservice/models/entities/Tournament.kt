package com.youcode.kingsleague.tournamentservice.models.entities

import com.youcode.kingsleague.tournamentservice.models.transients.Organizer
import com.youcode.kingsleague.tournamentservice.models.transients.Team
import jakarta.persistence.*
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
open class Tournament(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @NotNull(message = "name can't be null") var name: String,
    @Temporal(TemporalType.DATE) @Future @NotNull(message = "debut date can't be null") var debutDate: LocalDate,
    @Temporal(TemporalType.DATE) @Future @NotNull(message = "end date can't be null") var endDate: LocalDate,
    @NotNull(message = "location can't be null") var location: String,
    var organizerId: Long,
    @Transient val organizer: Organizer,
    @CreationTimestamp @Column(nullable = false, updatable = false, insertable = false, name = "created_at") var createdAt: LocalDateTime,
    @UpdateTimestamp @Column(nullable = false, updatable = false, insertable = false, name = "updated_at") var updatedAt: LocalDateTime,
) {
    @Transient val teams: List<Team> = listOf()
}