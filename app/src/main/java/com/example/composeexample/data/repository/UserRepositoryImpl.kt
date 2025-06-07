package com.example.composeexample.data.repository

import com.example.composeexample.data.mapper.UserMapper
import com.example.composeexample.data.remote.api.ApiService
import com.example.composeexample.domain.model.User
import com.example.composeexample.domain.repository.UserRepository

class UserRepositoryImpl(
    private val apiService: ApiService
) : UserRepository {

    override suspend fun getUsers(): Result<List<User>> {
        return try {
            val usersDto = apiService.getUsers()
            val users = UserMapper.fromDtoList(usersDto)
            Result.success(users)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUser(id: Int): Result<User> {
        return try {
            val userDto = apiService.getUser(id)
            val user = UserMapper.fromDto(userDto)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}