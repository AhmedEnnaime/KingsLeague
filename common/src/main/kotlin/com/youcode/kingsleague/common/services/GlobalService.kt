package com.youcode.kingsleague.common.services

interface GlobalService<DTO, Identifier> {
    fun save(dto: DTO?): DTO?
    fun getAll(): List<DTO?>?
    fun update(identifier: Identifier, dto: DTO?): DTO?
    fun delete(identifier: Identifier)
    fun findByID(identifier: Identifier): DTO?
}