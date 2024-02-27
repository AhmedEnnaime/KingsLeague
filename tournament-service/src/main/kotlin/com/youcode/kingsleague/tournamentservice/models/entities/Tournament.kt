package com.youcode.kingsleague.tournamentservice.models.entities

import com.youcode.kingsleague.tournamentservice.models.transients.Organizer
import com.youcode.kingsleague.tournamentservice.models.transients.TournamentTeam
import jakarta.persistence.*
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tournament_type", discriminatorType = DiscriminatorType.STRING)
open class Tournament(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) open var id: Long,
    @NotNull(message = "name can't be null") open var name: String,
    @Temporal(TemporalType.DATE) @Future @NotNull(message = "debut date can't be null") open var debutDate: LocalDate,
    @Temporal(TemporalType.DATE) @Future @NotNull(message = "end date can't be null") open var endDate: LocalDate,
    @NotNull(message = "location can't be null") open var location: String,
    @NotNull(message = "maximum number of teams should not be null") open var teamsNum: Int,
    @Column(nullable = true) open var organizerId: Long,
    @Transient open val organizer: Organizer,
    @CreationTimestamp @Column(nullable = true, name = "created_at") open var createdAt: LocalDateTime?,
    @UpdateTimestamp @Column(nullable = true, name = "updated_at") open var updatedAt: LocalDateTime?,
) {
    @Transient open val teams: List<TournamentTeam> = listOf()
}