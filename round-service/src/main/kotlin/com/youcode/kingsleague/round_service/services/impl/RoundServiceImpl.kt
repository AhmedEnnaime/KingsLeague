package com.youcode.kingsleague.round_service.services.impl

import com.youcode.kingsleague.round_service.models.dto.RoundDTO
import com.youcode.kingsleague.round_service.repositories.RoundRepository
import com.youcode.kingsleague.round_service.services.RoundService
import com.youcode.kingsleague.round_service.services.client.CupServiceClient
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class RoundServiceImpl(private val modelMapper: ModelMapper, private val roundRepository: RoundRepository, private val cupServiceClient: CupServiceClient): RoundService {
    override fun save(dto: RoundDTO): RoundDTO {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<RoundDTO?>? {
        TODO("Not yet implemented")
    }

    override fun update(identifier: Long, dto: RoundDTO): RoundDTO {
        TODO("Not yet implemented")
    }

    override fun delete(identifier: Long) {
        TODO("Not yet implemented")
    }

    override fun findByID(identifier: Long): RoundDTO? {
        TODO("Not yet implemented")
    }
}