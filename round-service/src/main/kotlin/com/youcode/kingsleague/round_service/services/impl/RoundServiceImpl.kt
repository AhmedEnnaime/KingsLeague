package com.youcode.kingsleague.round_service.services.impl

import com.youcode.kingsleague.common.exceptions.ResourceNotFoundException
import com.youcode.kingsleague.round_service.models.dto.RoundDTO
import com.youcode.kingsleague.round_service.models.entities.Round
import com.youcode.kingsleague.round_service.models.transients.Cup
import com.youcode.kingsleague.round_service.repositories.RoundRepository
import com.youcode.kingsleague.round_service.services.RoundService
import com.youcode.kingsleague.round_service.services.client.CupServiceClient
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RoundServiceImpl(private val modelMapper: ModelMapper, private val roundRepository: RoundRepository, private val cupServiceClient: CupServiceClient): RoundService {
    override fun save(dto: RoundDTO): RoundDTO {
        dto.createdAt = LocalDateTime.now()
        dto.updatedAt = LocalDateTime.now()
        val cup: Cup = cupServiceClient.findCupById(dto.tournamentId)
        dto.cup = cup
        val roundEntity: Round = modelMapper.map(dto, Round::class.java)
        val savedRound: Round = roundRepository.save(roundEntity)
        return modelMapper.map(savedRound, RoundDTO::class.java)
    }

    override fun getAll(): List<RoundDTO?>? {
        val rounds: List<Round> = roundRepository.findAll()
        return rounds.map { round ->
            val cup: Cup = cupServiceClient.findCupById(round.tournamentId)
            val roundDTO: RoundDTO = modelMapper.map(round, RoundDTO::class.java)
            roundDTO.cup = cup
            roundDTO
        }
    }

    override fun update(identifier: Long, dto: RoundDTO): RoundDTO {
        val existingRound: Round = roundRepository.findById(identifier)
            .orElseThrow { ResourceNotFoundException("Round with id $identifier not found") }
        cupServiceClient.findCupById(dto.cup!!.id)
        existingRound.apply {
            dto.let {
                this.date = it.date
                this.tournamentId = it.tournamentId
                this.updatedAt = LocalDateTime.now()
            }
        }
        val updatedRound: Round = roundRepository.save(existingRound)
        return modelMapper.map(updatedRound, RoundDTO::class.java)
    }

    override fun delete(identifier: Long) {
        if (!roundRepository.existsById(identifier))
            throw ResourceNotFoundException("Round with id $identifier not found")
        roundRepository.deleteById(identifier)
    }

    override fun findByID(identifier: Long): RoundDTO? {
        val round: Round = roundRepository.findById(identifier).orElseThrow {
            ResourceNotFoundException("Round with id $identifier not found")
        }
        val roundDTO: RoundDTO =  modelMapper.map(round, RoundDTO::class.java)
        roundDTO.cup = cupServiceClient.findCupById(round.tournamentId)
       return roundDTO
    }
}