package com.youcode.kingsleague.tournamentservice.services.impl

import com.youcode.kingsleague.common.exceptions.ResourceNotFoundException
import com.youcode.kingsleague.tournamentservice.models.dto.CupDTO
import com.youcode.kingsleague.tournamentservice.models.dto.LeagueDTO
import com.youcode.kingsleague.tournamentservice.models.dto.TournamentDTO
import com.youcode.kingsleague.tournamentservice.models.entities.Cup
import com.youcode.kingsleague.tournamentservice.models.entities.League
import com.youcode.kingsleague.tournamentservice.models.entities.Tournament
import com.youcode.kingsleague.tournamentservice.repositories.CupRepository
import com.youcode.kingsleague.tournamentservice.repositories.LeagueRepository
import com.youcode.kingsleague.tournamentservice.repositories.TournamentRepository
import com.youcode.kingsleague.tournamentservice.services.TournamentService
import jakarta.persistence.DiscriminatorValue
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TournamentServiceImpl(private val tournamentRepository: TournamentRepository, private val modelMapper: ModelMapper, private val leagueRepository: LeagueRepository, private  val cupRepository: CupRepository): TournamentService {
    override fun createLeague(leagueDTO: LeagueDTO): LeagueDTO {
        leagueDTO.createdAt = LocalDateTime.now()
        leagueDTO.updatedAt = LocalDateTime.now()
        val leagueEntity: League = modelMapper.map(leagueDTO, League::class.java)
        val savedLeague: League = tournamentRepository.save(leagueEntity)
        return modelMapper.map(savedLeague, LeagueDTO::class.java)
    }

    override fun createCup(cupDTO: CupDTO): CupDTO {
        cupDTO.createdAt = LocalDateTime.now()
        cupDTO.updatedAt = LocalDateTime.now()
        val cupEntity: Cup = modelMapper.map(cupDTO, Cup::class.java)
        val savedCup: Cup = tournamentRepository.save(cupEntity)
        return modelMapper.map(savedCup, CupDTO::class.java)
    }

    override fun findAll(): List<TournamentDTO> {
        val tournaments: List<Tournament> = tournamentRepository.findAll()
        return tournaments.map { tournament ->
            val dto = modelMapper.map(tournament, TournamentDTO::class.java)
            dto.tournamentType = tournament.javaClass.getAnnotation(DiscriminatorValue::class.java)?.value
            dto
        }
    }

    override fun findById(id: Long): TournamentDTO {
        val tournament: Tournament = tournamentRepository.findById(id).orElseThrow {
            ResourceNotFoundException("Tournament with id $id not found")
        }
        val dto = modelMapper.map(tournament, TournamentDTO::class.java)
        dto.tournamentType = tournament.javaClass.getAnnotation(DiscriminatorValue::class.java)?.value // Set the tournamentType
        return dto
    }

    override fun findLeagueById(id: Long): LeagueDTO {
        val league: League = leagueRepository.findById(id).orElseThrow {
            ResourceNotFoundException("League with id $id not found")
        }
        return modelMapper.map(league, LeagueDTO::class.java)
    }

    override fun findCupById(id: Long): CupDTO {
        val cup: Cup = cupRepository.findById(id).orElseThrow {
            ResourceNotFoundException("Cup with id $id not found")
        }
        return modelMapper.map(cup, CupDTO::class.java)
    }


    override fun update(id: Long, tournamentDTO: TournamentDTO): TournamentDTO {
        val existingTournament: Tournament = tournamentRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Tournament with id $id not found") }
        tournamentDTO.updatedAt = LocalDateTime.now()
        existingTournament.apply {
            tournamentDTO.let {
                this.name = it.name
                this.location = it.location
                this.debutDate = it.debutDate
                this.endDate = it.endDate
            }
        }
        val updatedTournament: Tournament = tournamentRepository.save(existingTournament)
        return modelMapper.map(updatedTournament, TournamentDTO::class.java)
    }

    override fun delete(id: Long) {
        if (!tournamentRepository.existsById(id))
            throw ResourceNotFoundException("Tournament with id $id not found")
        tournamentRepository.deleteById(id)
    }

    override fun findLeagues(): List<LeagueDTO> {
        TODO("Not yet implemented")
    }

    override fun findCups(): List<CupDTO> {
        TODO("Not yet implemented")
    }
}