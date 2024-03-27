package com.youcode.kingsleague.team_service.services.impl

import com.youcode.kingsleague.common.exceptions.ResourceNotFoundException
import com.youcode.kingsleague.team_service.models.dto.TeamDTO
import com.youcode.kingsleague.team_service.models.entities.Team
import com.youcode.kingsleague.team_service.repositories.TeamRepository
import io.mockk.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.modelmapper.ModelMapper
import java.time.LocalDateTime
import java.util.*


class TeamServiceImplTest {
    private val teamRepository: TeamRepository = mockk()
    private val modelMapper: ModelMapper = mockk()
    private val teamServiceImpl = TeamServiceImpl(teamRepository, modelMapper)
    private val team = Team(
        id = 1L,
        name = "Team Name",
        country = "Country Name",
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
        matches = listOf()
    )
    private val teamDTO = TeamDTO(
        id = 1L,
        name = "Team Name",
        country = "Country Name",
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
    )

    @Test
    @DisplayName("Test create method in a success scenario")
    fun testSuccessCreate() {
        every { modelMapper.map(teamDTO, Team::class.java) } returns team
        every { modelMapper.map(team, TeamDTO::class.java) } returns teamDTO
        every { teamRepository.save(team) } returns team
        val savedTeam: TeamDTO = teamServiceImpl.save(teamDTO)
        assertThat(savedTeam).isNotNull
    }


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

    @Test
    @DisplayName("Test get all method when the list is not empty")
    fun testNotEmptyFindAll() {
        every { teamRepository.findAll() } returns listOf(team)
        every { modelMapper.map(team, TeamDTO::class.java) } returns teamDTO
        val allTeams: List<TeamDTO?>? = teamServiceImpl.getAll()
        verify(exactly = 1) { teamRepository.findAll() }
        assertThat(allTeams).isNotNull.hasSize(1)
    }

    @Test
    @DisplayName("Test get all method when the list is empty")
    fun testEmptyFindAll() {
        every { teamRepository.findAll() } returns emptyList()
        val allTeams: List<TeamDTO?>? = teamServiceImpl.getAll()
        assertThat(allTeams).isEmpty()
    }

    @Test
    @DisplayName("Test delete method when the id is valid")
    fun testDeleteWhenIdIsValid() {
        every { teamRepository.existsById(1L) } returns true
        every { teamRepository.deleteById(1L) } just runs
        teamServiceImpl.delete(1L)
        verify(exactly = 1) { teamRepository.deleteById(1L) }
    }

    @Test
    @DisplayName("Test delete method when the id is not valid")
    fun testDeleteWhenIdIsInvalid() {
        every { teamRepository.existsById(999L) } returns false
        assertThrows(ResourceNotFoundException::class.java) { teamServiceImpl.delete(999L) }
        verify(exactly = 0) { teamRepository.deleteById(999L) }
    }

    @Test
    @DisplayName("Test update method when the id is valid")
    fun testUpdateWhenIdIsValid() {
        every { teamRepository.findById(1L) } returns Optional.of(team)
        every { modelMapper.map(team, TeamDTO::class.java) } returns teamDTO
        every { teamRepository.save(team) } returns team
        val updatedTeam: TeamDTO = teamServiceImpl.update(1L, teamDTO)
        verify(exactly = 1) { teamRepository.save(team) }
        assertThat(updatedTeam).isNotNull
    }

    @Test
    @DisplayName("Test update method when the id is not valid")
    fun testUpdateWhenIdIsNotValid() {
        every { teamRepository.findById(999L) } returns Optional.empty()
        assertThrows(ResourceNotFoundException::class.java) { teamServiceImpl.update(999L, teamDTO) }
        verify(exactly = 1) { teamRepository.findById(999L) }
    }
}