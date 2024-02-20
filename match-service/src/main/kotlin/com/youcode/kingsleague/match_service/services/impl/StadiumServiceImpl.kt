package com.youcode.kingsleague.match_service.services.impl

import com.youcode.kingsleague.common.exceptions.ResourceNotFoundException
import com.youcode.kingsleague.match_service.models.dto.StadiumDTO
import com.youcode.kingsleague.match_service.models.entities.Stadium
import com.youcode.kingsleague.match_service.repositories.StadiumRepository
import com.youcode.kingsleague.match_service.services.StadiumService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class StadiumServiceImpl(private val modelMapper: ModelMapper, private val stadiumRepository: StadiumRepository): StadiumService {
    override fun save(dto: StadiumDTO): StadiumDTO {
        dto.createdAt = LocalDateTime.now()
        dto.updatedAt = LocalDateTime.now()
        val stadiumEntity: Stadium = modelMapper.map(dto, Stadium::class.java)
        val savedStadium: Stadium = stadiumRepository.save(stadiumEntity)
        return modelMapper.map(savedStadium, StadiumDTO::class.java)
    }

    override fun getAll(): List<StadiumDTO?>? {
        val stadiums: List<Stadium> = stadiumRepository.findAll()
        return stadiums.map { stadium -> modelMapper.map(stadium, StadiumDTO::class.java) }
    }

    override fun update(identifier: Long, dto: StadiumDTO): StadiumDTO {
        TODO("Not yet implemented")
    }

    override fun delete(identifier: Long) {
        if (!stadiumRepository.existsById(identifier))
            throw ResourceNotFoundException("Stadium with id $identifier not found")
        stadiumRepository.deleteById(identifier)
    }

    override fun findByID(identifier: Long): StadiumDTO? {
        val stadium: Stadium = stadiumRepository.findById(identifier).orElseThrow {
            ResourceNotFoundException("Stadium with id $identifier not found")
        }
        return modelMapper.map(stadium, StadiumDTO::class.java)
    }
}