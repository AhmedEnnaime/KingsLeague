package com.youcode.kingsleague.match_service.repositories

import com.youcode.kingsleague.match_service.models.embaddables.MatchRefereeKey
import com.youcode.kingsleague.match_service.models.entities.MatchReferee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MatchRefereeRepository: JpaRepository<MatchReferee, MatchRefereeKey> {
}