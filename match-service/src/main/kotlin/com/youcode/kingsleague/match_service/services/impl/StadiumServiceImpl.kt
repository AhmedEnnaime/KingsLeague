package com.youcode.kingsleague.match_service.services.impl

import com.youcode.kingsleague.match_service.models.dto.StadiumDTO
import com.youcode.kingsleague.match_service.repositories.StadiumRepository
import com.youcode.kingsleague.match_service.services.StadiumService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class StadiumServiceImpl(private val modelMapper: ModelMapper, private val stadiumRepository: StadiumRepository): StadiumService {
    override fun save(dto: StadiumDTO): StadiumDTO {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<StadiumDTO?>? {
        TODO("Not yet implemented")
    }

    override fun update(identifier: Long, dto: StadiumDTO): StadiumDTO {
        TODO("Not yet implemented")
    }

    override fun delete(identifier: Long) {
        TODO("Not yet implemented")
    }

    override fun findByID(identifier: Long): StadiumDTO? {
        TODO("Not yet implemented")
    }
}