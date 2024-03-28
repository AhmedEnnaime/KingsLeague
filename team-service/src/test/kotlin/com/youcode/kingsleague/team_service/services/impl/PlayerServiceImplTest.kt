package com.youcode.kingsleague.team_service.services.impl

import com.youcode.kingsleague.common.exceptions.ResourceNotFoundException
import com.youcode.kingsleague.team_service.models.dto.PlayerDTO
import com.youcode.kingsleague.team_service.models.entities.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import com.youcode.kingsleague.team_service.repositories.PlayerRepository
import io.mockk.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.modelmapper.ModelMapper
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

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

    @Test
    @DisplayName("Test get all method when the list is not empty")
    fun testNotEmptyGetAll() {
        every { playerRepository.findAll() } returns listOf(player)
        every { modelMapper.map(player, PlayerDTO::class.java) } returns playerDTO
        val allPlayers: List<PlayerDTO?>? = playerServiceImpl.getAll()
        verify(exactly = 1) { playerRepository.findAll() }
        assertThat(allPlayers).isNotNull.hasSize(1)
    }

    @Test
    @DisplayName("Test get all method when the list is empty")
    fun testEmptyGetAll() {
        every { playerRepository.findAll() } returns emptyList()
        val allPlayers: List<PlayerDTO?>? = playerServiceImpl.getAll()
        assertThat(allPlayers).isEmpty()
    }

    @Test
    @DisplayName("Test delete method when the id is valid")
    fun testDeleteWhenIdIsValid() {
        every { playerRepository.existsById(1L) } returns true
        every { playerRepository.deleteById(1L) } just runs
        playerServiceImpl.delete(1L)
        verify(exactly = 1) { playerRepository.deleteById(1L) }
    }

    @Test
    @DisplayName("Test delete method when the id is valid")
    fun testDeleteWhenIdIsNotValid() {
        every { playerRepository.existsById(999L) } returns false
        assertThrows(ResourceNotFoundException::class.java) { playerServiceImpl.delete(999L) }
        verify(exactly = 0) { playerRepository.deleteById(999L) }
    }

    @Test
    @DisplayName("Test find by id method when the id is valid")
    fun testFindByIdWhenIdIsValid() {
        every { playerRepository.findById(1L) } returns Optional.of(player)
        every { modelMapper.map(player, PlayerDTO::class.java) } returns playerDTO
        val foundPlayer: PlayerDTO? = playerServiceImpl.findByID(1L)
        verify(exactly = 1) { playerRepository.findById(1L) }
        assertThat(foundPlayer)
    }

    @Test
    @DisplayName("Test find by id method when the id is not valid")
    fun testFindByIdWhenIdIsNotValid() {
        every { playerRepository.findById(999L) } returns Optional.empty()
        assertThrows(ResourceNotFoundException::class.java) { playerServiceImpl.findByID(999L) }
        verify(exactly = 1) { playerRepository.findById(999L) }
    }

    @Test
    @DisplayName("Test update method when the id is valid")
    fun testUpdateWhenIdIsValid() {
        every { playerRepository.findById(1L) } returns Optional.of(player)
        every { modelMapper.map(player, PlayerDTO::class.java) } returns playerDTO
        every { playerRepository.save(player) } returns player
        val updatedPlayer: PlayerDTO = playerServiceImpl.update(1L, playerDTO)
        verify(exactly = 1) { playerRepository.save(player) }
        assertThat(updatedPlayer).isNotNull
    }

    @Test
    @DisplayName("Test update method when the id is not valid")
    fun testUpdateWhenIdIsNotValid() {
        every { playerRepository.findById(999L) } returns Optional.empty()
        assertThrows(ResourceNotFoundException::class.java) { playerServiceImpl.update(999L, playerDTO) }
        verify(exactly = 1) { playerRepository.findById(999L) }
    }

}