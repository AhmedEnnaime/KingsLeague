package com.youcode.kingsleague.match_service.services

import com.youcode.kingsleague.match_service.models.dto.MatchDTO
import com.youcode.kingsleague.match_service.models.dto.RetrievalMatchDTO

interface MatchService {
    fun save(dto: MatchDTO): MatchDTO
    fun findAll(): List<RetrievalMatchDTO?>?
    fun findByID(identifier: Long): RetrievalMatchDTO?
    fun delete(identifier: Long)
    fun update(identifier: Long, dto: MatchDTO): MatchDTO

}