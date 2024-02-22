package com.youcode.kingsleague.match_service.services.impl

import com.youcode.kingsleague.match_service.models.dto.MatchRefereeDTO
import com.youcode.kingsleague.match_service.repositories.MatchRefereeRepository
import com.youcode.kingsleague.match_service.services.MatchRefereeService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class MatchRefereeServiceImpl(private val matchRefereeRepository: MatchRefereeRepository, private val modelMapper: ModelMapper): MatchRefereeService {
    override fun assignRefereeToMatch(matchRefereeDTO: MatchRefereeDTO): MatchRefereeDTO {

        TODO("Not yet implemented")
    }

}