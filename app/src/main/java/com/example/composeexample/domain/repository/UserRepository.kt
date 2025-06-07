package com.example.composeexample.domain.repository

import com.example.composeexample.domain.model.User

interface UserRepository {
    suspend fun getUsers(): Result<List<User>>
    suspend fun getUser(id: Int): Result<User>
}