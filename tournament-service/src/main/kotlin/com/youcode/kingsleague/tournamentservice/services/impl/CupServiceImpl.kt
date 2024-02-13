package com.youcode.kingsleague.tournamentservice.services.impl

import com.youcode.kingsleague.tournamentservice.models.dto.CupDTO
import com.youcode.kingsleague.tournamentservice.repositories.CupRepository
import com.youcode.kingsleague.tournamentservice.services.CupService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class CupServiceImpl(private val cupRepository: CupRepository, private val modelMapper: ModelMapper): CupService {
    override fun save(dto: CupDTO): CupDTO {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<CupDTO?>? {
        TODO("Not yet implemented")
    }

    override fun update(identifier: Long, dto: CupDTO): CupDTO {
        TODO("Not yet implemented")
    }

    override fun delete(identifier: Long) {
        TODO("Not yet implemented")
    }

    override fun findByID(identifier: Long): CupDTO? {
        TODO("Not yet implemented")
    }
}