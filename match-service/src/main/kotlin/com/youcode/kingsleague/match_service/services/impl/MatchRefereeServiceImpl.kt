package com.youcode.kingsleague.match_service.services.impl

import com.youcode.kingsleague.common.exceptions.RefereeAlreadyAssignedInMatch
import com.youcode.kingsleague.match_service.models.dto.MatchDTO
import com.youcode.kingsleague.match_service.models.dto.MatchRefereeDTO
import com.youcode.kingsleague.match_service.models.dto.RefereeDTO
import com.youcode.kingsleague.match_service.models.dto.RetrievalMatchDTO
import com.youcode.kingsleague.match_service.models.embaddables.MatchRefereeKey
import com.youcode.kingsleague.match_service.models.entities.MatchReferee
import com.youcode.kingsleague.match_service.repositories.MatchRefereeRepository
import com.youcode.kingsleague.match_service.services.MatchRefereeService
import com.youcode.kingsleague.match_service.services.MatchService
import com.youcode.kingsleague.match_service.services.RefereeService
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class MatchRefereeServiceImpl(private val matchRefereeRepository: MatchRefereeRepository, private val modelMapper: ModelMapper, private val matchService: MatchService, private val refereeService: RefereeService): MatchRefereeService {
    override fun assignRefereeToMatch(matchRefereeDTO: MatchRefereeDTO): MatchRefereeDTO {
        val referee: RefereeDTO? = matchRefereeDTO.referee?.id?.let { refereeService.findByID(it) }
        val match: RetrievalMatchDTO? = matchRefereeDTO.match?.id?.let { matchService.findByID(it) }
        val matchRefereeKey = MatchRefereeKey(matchId = match?.id!!, refereeId = referee?.id!!)
        if (matchRefereeRepository.existsById(matchRefereeKey)) {
            throw RefereeAlreadyAssignedInMatch("This referee is already assigned in this match")
        }else {
            matchRefereeDTO.id = matchRefereeKey
        }
        matchRefereeDTO.match = match
        matchRefereeDTO.referee = referee
        val savedMatchReferee: MatchReferee = matchRefereeRepository.save(modelMapper.map(matchRefereeDTO, MatchReferee::class.java))
        return modelMapper.map(savedMatchReferee, MatchRefereeDTO::class.java)
    }

    override fun findMatchReferees(matchId: Long): List<RefereeDTO> {
        TODO("Not yet implemented")
    }

    override fun findRefereeMatches(refereeId: Long): List<MatchDTO> {
        TODO("Not yet implemented")
    }

}