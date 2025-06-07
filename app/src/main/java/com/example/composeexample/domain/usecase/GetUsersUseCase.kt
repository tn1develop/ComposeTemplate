package com.example.composeexample.domain.usecase

import com.example.composeexample.domain.model.User
import com.example.composeexample.domain.repository.UserRepository

class GetUsersUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<List<User>> {
        return userRepository.getUsers()
    }
}