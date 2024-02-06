package com.youcode.kingsleague.team_service.services.impl

import com.youcode.kingsleague.team_service.models.dto.PlayerDTO
import com.youcode.kingsleague.team_service.repositories.PlayerRepository
import com.youcode.kingsleague.team_service.services.PlayerService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class PlayerServiceImpl(private val playerRepository: PlayerRepository, private val modelMapper: ModelMapper): PlayerService {
    override fun save(dto: PlayerDTO?): PlayerDTO? {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<PlayerDTO?>? {
        TODO("Not yet implemented")
    }

    override fun update(identifier: Long, dto: PlayerDTO?): PlayerDTO? {
        TODO("Not yet implemented")
    }

    override fun delete(identifier: Long) {
        TODO("Not yet implemented")
    }

    override fun findByID(identifier: Long): PlayerDTO? {
        TODO("Not yet implemented")
    }
}