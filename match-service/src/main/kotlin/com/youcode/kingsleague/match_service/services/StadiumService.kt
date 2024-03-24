package com.youcode.kingsleague.match_service.services

import com.youcode.kingsleague.common.services.GlobalService
import com.youcode.kingsleague.match_service.models.dto.StadiumDTO
import org.springframework.web.multipart.MultipartFile

interface StadiumService: GlobalService<StadiumDTO, Long> {
    fun saveWithImage(dto: StadiumDTO, file: MultipartFile): StadiumDTO
}