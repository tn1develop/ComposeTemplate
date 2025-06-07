package com.example.composeexample.data.remote.api

import com.example.composeexample.data.remote.dto.UserDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(private val httpClient: HttpClient) {
    suspend fun getUsers(): List<UserDto> {
        return httpClient.get("https://jsonplaceholder.typicode.com/users").body()
    }

    suspend fun getUser(id: Int): UserDto {
        return httpClient.get("https://jsonplaceholder.typicode.com/users/$id").body()
    }
}