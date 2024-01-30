package com.youcode.kingsleague.organizer_service.repositories

import com.youcode.kingsleague.organizer_service.models.entities.Organizer
import org.springframework.data.jpa.repository.JpaRepository

interface OrganizerRepository : JpaRepository<Organizer, Long>{
}