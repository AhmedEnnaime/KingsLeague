package com.youcode.kingsleague.models.entities

import com.youcode.kingsleague.models.enums.TournamentType
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import lombok.Builder
import java.time.LocalDate

@Entity
@Table(name = "tournaments")
@Builder
class Tournament (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column(nullable = false) @NotNull(message = "name can't be null") var name: String,
    @Temporal(TemporalType.DATE) var debut_date: LocalDate,
    @Temporal(TemporalType.DATE) var end_date: LocalDate,
    @Column(nullable = false) @NotNull var location: String,
    @ManyToOne @JoinColumn(name = "organizer_id", nullable = false) val organizer: Organizer
) {

}
