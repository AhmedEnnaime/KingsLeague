package com.youcode.kingsleague.team_service.services.impl

import com.youcode.kingsleague.common.exceptions.ResourceNotFoundException
import com.youcode.kingsleague.team_service.models.dto.TeamDTO
import com.youcode.kingsleague.team_service.models.entities.Team
import com.youcode.kingsleague.team_service.repositories.TeamRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.modelmapper.ModelMapper
import java.time.LocalDateTime
import java.util.*


class TeamServiceImplTest {
    val teamRepository: TeamRepository = mockk()
    val modelMapper: ModelMapper = mockk()
    val teamServiceImpl = TeamServiceImpl(teamRepository, modelMapper)
    val team = Team(
        id = 1,
        name = "Team Name",
        country = "Country Name",
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
        matches = listOf()
    )
    val teamDTO = TeamDTO(
        id = 1,
        name = "Team Name",
        country = "Country Name",
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
    )

    @Test
    @DisplayName("Test find by id method when the id is valid")
    fun testSuccessFindById() {
        every { teamRepository.findById(1L) } returns Optional.of(team)
        every { modelMapper.map(team, TeamDTO::class.java) } returns teamDTO
        val foundTeam: TeamDTO? = teamServiceImpl.findByID(1L)
        verify(exactly = 1) { teamRepository.findById(1L) }
        assertThat(foundTeam)
    }

    @Test
    @DisplayName("Test find by id method when the id is not valid")
    fun testFindByIdWhenIdIsNotValid() {
        every { teamRepository.findById(999L) } returns Optional.empty()
        assertThrows(ResourceNotFoundException::class.java) { teamServiceImpl.findByID(999L) }
        verify(exactly = 1) { teamRepository.findById(999L) }
    }
}