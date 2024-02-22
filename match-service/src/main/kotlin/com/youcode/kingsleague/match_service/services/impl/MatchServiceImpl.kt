package com.youcode.kingsleague.match_service.services.impl

import com.youcode.kingsleague.match_service.models.dto.MatchDTO
import com.youcode.kingsleague.match_service.repositories.MatchRepository
import com.youcode.kingsleague.match_service.services.MatchService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class MatchServiceImpl(private val matchRepository: MatchRepository, private val modelMapper: ModelMapper): MatchService {
    override fun save(dto: MatchDTO): MatchDTO {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<MatchDTO?>? {
        TODO("Not yet implemented")
    }

    override fun update(identifier: Long, dto: MatchDTO): MatchDTO {
        TODO("Not yet implemented")
    }

    override fun delete(identifier: Long) {
        TODO("Not yet implemented")
    }

    override fun findByID(identifier: Long): MatchDTO? {
        TODO("Not yet implemented")
    }
}