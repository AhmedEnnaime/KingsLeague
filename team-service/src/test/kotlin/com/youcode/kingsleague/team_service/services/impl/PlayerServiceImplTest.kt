package com.youcode.kingsleague.team_service.services.impl

import com.youcode.kingsleague.team_service.models.dto.PlayerDTO
import com.youcode.kingsleague.team_service.models.entities.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import com.youcode.kingsleague.team_service.repositories.PlayerRepository
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.modelmapper.ModelMapper
import java.time.LocalDate
import java.time.LocalDateTime

class PlayerServiceImplTest {
    private val playerRepository: PlayerRepository = mockk()
    private val modelMapper: ModelMapper = mockk()
    private val playerServiceImpl = PlayerServiceImpl(playerRepository, modelMapper)
    private val player = Player(
        id = 1L,
        firstName = "John",
        lastName = "Doe",
        weight = 75.0,
        height = 180.0,
        birthday = LocalDate.of(1990, 5, 15),
        nationality = "USA",
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
    )
    val playerDTO = PlayerDTO(
        id = 1L,
        firstName = "John",
        lastName = "Doe",
        weight = 75.0,
        height = 180.0,
        birthday = LocalDate.of(1990, 5, 15),
        nationality = "USA",
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
    )

    @Test
    @DisplayName("Test create method in a success scenario")
    fun testCreate() {
        every { modelMapper.map(playerDTO, Player::class.java) } returns player
        every { modelMapper.map(player, PlayerDTO::class.java) } returns playerDTO
        every { playerRepository.save(player) } returns player
        val savedPlayer: PlayerDTO = playerServiceImpl.save(playerDTO)
        assertThat(savedPlayer).isNotNull
    }

}