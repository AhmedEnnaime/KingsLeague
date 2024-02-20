package com.youcode.kingsleague.match_service.services.impl

import com.youcode.kingsleague.common.exceptions.ResourceNotFoundException
import com.youcode.kingsleague.match_service.models.dto.RefereeDTO
import com.youcode.kingsleague.match_service.models.entities.Referee
import com.youcode.kingsleague.match_service.repositories.RefereeRepository
import com.youcode.kingsleague.match_service.services.RefereeService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RefereeServiceImpl(private val modelMapper: ModelMapper, private  val refereeRepository: RefereeRepository): RefereeService {
    override fun save(dto: RefereeDTO): RefereeDTO {
        dto.createdAt = LocalDateTime.now()
        dto.updatedAt = LocalDateTime.now()
        val refereeEntity: Referee = modelMapper.map(dto, Referee::class.java)
        val savedEntity: Referee = refereeRepository.save(refereeEntity)
        return modelMapper.map(savedEntity, RefereeDTO::class.java)
    }

    override fun getAll(): List<RefereeDTO?>? {
        val referees: List<Referee> = refereeRepository.findAll()
        return referees.map { referee -> modelMapper.map(referee, RefereeDTO::class.java) }
    }

    override fun update(identifier: Long, dto: RefereeDTO): RefereeDTO {
        val existingReferee: Referee = refereeRepository.findById(identifier)
            .orElseThrow { ResourceNotFoundException("Referee with id $identifier not found") }
        dto.updatedAt = LocalDateTime.now()
        existingReferee.apply {
            dto.let {
                this.name = it.name
                this.nationality = it.nationality
            }
        }
        val updatedReferee: Referee = refereeRepository.save(existingReferee)
        return modelMapper.map(updatedReferee, RefereeDTO::class.java)
    }

    override fun delete(identifier: Long) {
        if (!refereeRepository.existsById(identifier))
            throw ResourceNotFoundException("Stadium with id $identifier not found")
        refereeRepository.deleteById(identifier)
    }

    override fun findByID(identifier: Long): RefereeDTO? {
        val referee: Referee = refereeRepository.findById(identifier).orElseThrow {
            ResourceNotFoundException("Referee with id $identifier not found")
        }
        return modelMapper.map(referee, RefereeDTO::class.java)
    }
}