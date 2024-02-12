package com.youcode.kingsleague.team_service.services.impl

import com.youcode.kingsleague.common.exceptions.ResourceNotFoundException
import com.youcode.kingsleague.team_service.models.dto.PlayerDTO
import com.youcode.kingsleague.team_service.models.entities.Player
import com.youcode.kingsleague.team_service.repositories.PlayerRepository
import com.youcode.kingsleague.team_service.services.PlayerService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PlayerServiceImpl(private val playerRepository: PlayerRepository, private val modelMapper: ModelMapper): PlayerService {
    override fun save(dto: PlayerDTO): PlayerDTO {
        dto.createdAt = LocalDateTime.now()
        dto.updatedAt = LocalDateTime.now()
        val playerEntity: Player = modelMapper.map(dto, Player::class.java)
        val savedPlayer: Player = playerRepository.save(playerEntity)
        return modelMapper.map(savedPlayer, PlayerDTO::class.java)
    }

    override fun getAll(): List<PlayerDTO?>? {
        val players: List<Player> = playerRepository.findAll()
        return players.map { player -> modelMapper.map(player, PlayerDTO::class.java)  }
    }

    override fun update(identifier: Long, dto: PlayerDTO): PlayerDTO {
        val existingPlayer: Player = playerRepository.findById(identifier)
            .orElseThrow {ResourceNotFoundException("Team with id $identifier not found")}
        dto.updatedAt = LocalDateTime.now()
        existingPlayer.apply {
            dto.let {
                this.firstName = it.firstName
                this.lastName = it.lastName
                this.birthday = it.birthday
                this.height = it.height
                this.weight = it.weight
                this.nationality = it.nationality
            }
        }
        return modelMapper.map(playerRepository.save(existingPlayer), PlayerDTO::class.java)
    }

    override fun delete(identifier: Long) {
        if (!playerRepository.existsById(identifier))
            throw ResourceNotFoundException("Team with id $identifier not found")
        playerRepository.deleteById(identifier)
    }

    override fun findByID(identifier: Long): PlayerDTO? {
        val player: Player = playerRepository.findById(identifier)
            .orElseThrow {ResourceNotFoundException("Team with id $identifier not found")}
        return modelMapper.map(player, PlayerDTO::class.java)
    }
}