package com.example.composeexample.data.mapper

import com.example.composeexample.data.remote.dto.UserDto
import com.example.composeexample.domain.model.User

object UserMapper {
    fun fromDto(dto: UserDto): User {
        return User(
            id = dto.id,
            name = dto.name,
            email = dto.email
        )
    }

    fun fromDtoList(dtoList: List<UserDto>): List<User> {
        return dtoList.map { fromDto(it) }
    }
}