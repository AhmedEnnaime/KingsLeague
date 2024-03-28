package com.youcode.kingsleague.team_service.services.impl

import com.youcode.kingsleague.common.exceptions.ResourceNotFoundException
import com.youcode.kingsleague.team_service.models.dto.PlayerDTO
import com.youcode.kingsleague.team_service.models.dto.TeamDTO
import com.youcode.kingsleague.team_service.models.dto.TeamPlayerDTO
import com.youcode.kingsleague.team_service.models.embeddables.TeamPlayerKey
import com.youcode.kingsleague.team_service.models.entities.Player
import com.youcode.kingsleague.team_service.models.entities.Team
import com.youcode.kingsleague.team_service.models.entities.TeamPlayer
import com.youcode.kingsleague.team_service.repositories.TeamPlayerRepository
import com.youcode.kingsleague.team_service.services.PlayerService
import com.youcode.kingsleague.team_service.services.TeamService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.modelmapper.ModelMapper
import java.time.LocalDate
import java.time.LocalDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class TeamPlayerServiceImplTest {
    private val teamPlayerRepository: TeamPlayerRepository = mockk()
    private val modelMapper: ModelMapper = mockk()
    private val teamService: TeamService = mockk()
    private val playerService: PlayerService = mockk()
    private val teamPlayerServiceImpl = TeamPlayerServiceImpl(teamPlayerRepository, teamService, playerService, modelMapper)
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
    private val team = Team(
        id = 1L,
        name = "Team Name",
        country = "Country Name",
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
        matches = listOf()
    )
    private val teamPlayerKey = TeamPlayerKey(team.id, player.id)
    private val teamPlayer = TeamPlayer(
        id = teamPlayerKey,
        player = player,
        team = team,
        joinedAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
    )
    private val playerDTO = PlayerDTO(
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
    private val teamDTO = TeamDTO(
        id = 1L,
        name = "Team Name",
        country = "Country Name",
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
    )

    private val teamPlayerDTO = TeamPlayerDTO(
        id = teamPlayerKey,
        player = playerDTO,
        team = teamDTO,
        joinedAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
    )

    @Test
    @DisplayName("Test create method in a success case scenario")
    fun testSuccessCreate() {
        every { teamService.findByID(team.id) } returns teamDTO
        every { playerService.findByID(player.id) } returns playerDTO
        every { teamPlayerRepository.existsById(teamPlayerKey) } returns false
        every { modelMapper.map(teamPlayer, TeamPlayerDTO::class.java) } returns teamPlayerDTO
        every { modelMapper.map(teamPlayerDTO, TeamPlayer::class.java) } returns teamPlayer
        every { teamPlayerRepository.save(teamPlayer) } returns teamPlayer
        val savedTeamPlayer: TeamPlayerDTO = teamPlayerServiceImpl.save(teamPlayerDTO)
        assertThat(savedTeamPlayer).isNotNull
    }

    @Test
    @DisplayName("Test create method when team id is not valid")
    fun testCreateWhenTeamIdIsInvalid() {
        every { teamService.findByID(team.id) } returns null
        Assertions.assertThrows(ResourceNotFoundException::class.java) { teamPlayerServiceImpl.save(teamPlayerDTO) }
        verify(exactly = 0) { playerService.findByID(any()) }
        verify(exactly = 0) { teamPlayerRepository.save(any()) }
    }

    @Test
    @DisplayName("Test create method when player id is not valid")
    fun testCreateWhenPlayerIdIsInvalid() {
        every { teamService.findByID(team.id) } returns teamDTO
        every { playerService.findByID(player.id) } returns null
        assertThrows(ResourceNotFoundException::class.java) { teamPlayerServiceImpl.save(teamPlayerDTO) }
        verify(exactly = 0) { teamPlayerRepository.save(any()) }
    }

    @Test
    @DisplayName("Test create method when player is already in a team")
    fun testCreateWhenPlayerAlreadyExistsInTeam() {
        every { teamService.findByID(team.id) } returns teamDTO
        every { playerService.findByID(player.id) } returns playerDTO
        every { teamPlayerRepository.existsById(teamPlayerKey) } returns true
        assertThrows(Exception::class.java) { teamPlayerServiceImpl.save(teamPlayerDTO) }
        verify(exactly = 0) { teamPlayerRepository.save(any()) }
    }


}