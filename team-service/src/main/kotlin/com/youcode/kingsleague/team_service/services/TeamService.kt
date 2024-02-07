package com.youcode.kingsleague.team_service.services

import com.youcode.kingsleague.common.services.GlobalService
import com.youcode.kingsleague.team_service.models.dto.TeamDTO

interface TeamService: GlobalService<TeamDTO, Long> {
}