package com.youcode.kingsleague.match_service.services.impl

import com.youcode.kingsleague.common.exceptions.ResourceNotFoundException
import com.youcode.kingsleague.match_service.models.dto.ResultDTO
import com.youcode.kingsleague.match_service.models.entities.Match
import com.youcode.kingsleague.match_service.repositories.ResultRepository
import com.youcode.kingsleague.match_service.services.ResultService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import com.youcode.kingsleague.match_service.models.entities.Result

@Service
class ResultServiceImpl(private val modelMapper: ModelMapper, private val resultRepository: ResultRepository): ResultService {
    override fun save(dto: ResultDTO): ResultDTO {
        dto.createdAt = LocalDateTime.now()
        dto.updatedAt = LocalDateTime.now()
        val resultEntity: Result = modelMapper.map(dto, Result::class.java)
        val savedResult: Result = resultRepository.save(resultEntity)
        return modelMapper.map(savedResult, ResultDTO::class.java)
    }

    override fun getAll(): List<ResultDTO?>? {
        val results: List<Result> = resultRepository.findAll()
        return results.map { result -> modelMapper.map(result, ResultDTO::class.java) }
    }

    override fun update(identifier: Long, dto: ResultDTO): ResultDTO {
        val existingResult: Result = resultRepository.findById(identifier)
            .orElseThrow { ResourceNotFoundException("Result with id $identifier not found") }
        dto.updatedAt = LocalDateTime.now()
        existingResult.apply {
            dto.let {
                this.score = it.score
                this.teamId = it.teamId!!
                this.match = modelMapper.map(it.match, Match::class.java)
            }
        }
        val updatedResult: Result = resultRepository.save(existingResult)
        return modelMapper.map(updatedResult, ResultDTO::class.java)
    }

    override fun delete(identifier: Long) {
        if (!resultRepository.existsById(identifier))
            throw ResourceNotFoundException("Stadium with id $identifier not found")
        resultRepository.deleteById(identifier)
    }

    override fun findByID(identifier: Long): ResultDTO? {
        val result: Result = resultRepository.findById(identifier).orElseThrow {
            ResourceNotFoundException("Result with id $identifier not found")
        }
        return modelMapper.map(result, ResultDTO::class.java)
    }
}