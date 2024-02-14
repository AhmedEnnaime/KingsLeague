package com.youcode.kingsleague.tournamentservice.services.impl

import com.youcode.kingsleague.common.exceptions.ResourceNotFoundException
import com.youcode.kingsleague.tournamentservice.models.dto.CupDTO
import com.youcode.kingsleague.tournamentservice.models.entities.Cup
import com.youcode.kingsleague.tournamentservice.repositories.CupRepository
import com.youcode.kingsleague.tournamentservice.services.CupService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CupServiceImpl(private val cupRepository: CupRepository, private val modelMapper: ModelMapper): CupService {
    override fun save(dto: CupDTO): CupDTO {
        dto.createdAt = LocalDateTime.now()
        dto.updatedAt = LocalDateTime.now()
        val cupEntity: Cup = modelMapper.map(dto, Cup::class.java)
        val savedCup: Cup = cupRepository.save(cupEntity)
        return modelMapper.map(savedCup, CupDTO::class.java)
    }

    override fun getAll(): List<CupDTO?>? {
        val cups: List<Cup> = cupRepository.findAll()
        return cups.map { cup -> modelMapper.map(cup, CupDTO::class.java) }
    }

    override fun update(identifier: Long, dto: CupDTO): CupDTO {
        val existingCup: Cup = cupRepository.findById(identifier).orElseThrow{
            ResourceNotFoundException("Cup with $identifier not found")
        }
        dto.updatedAt = LocalDateTime.now()
        existingCup.apply {
            dto.let {
                this.name = it.name
                this.location = it.location
                this.debutDate = it.debutDate
                this.endDate = it.endDate
            }
        }
        val updatedCup: Cup = cupRepository.save(existingCup)
        return modelMapper.map(updatedCup, CupDTO::class.java)
    }

    override fun delete(identifier: Long) {
        if (!cupRepository.existsById(identifier))
            throw ResourceNotFoundException("Cup with id $identifier not found")
        cupRepository.deleteById(identifier)
    }

    override fun findByID(identifier: Long): CupDTO? {
        val cup: Cup = cupRepository.findById(identifier).orElseThrow {
            ResourceNotFoundException("Cup with $identifier not found")
        }
        return modelMapper.map(cup, CupDTO::class.java)
    }
}