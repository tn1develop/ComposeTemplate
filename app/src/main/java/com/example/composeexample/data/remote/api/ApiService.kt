package com.example.composeexample.data.remote.api

import com.example.composeexample.BuildConfig
import com.example.composeexample.data.remote.dto.ResortDto
import com.example.composeexample.data.remote.dto.ResortRespDto
import com.example.composeexample.data.remote.dto.UserDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter

class ApiService(private val httpClient: HttpClient) {
    suspend fun getUsers(): List<UserDto> {
        return httpClient.get("https://jsonplaceholder.typicode.com/users").body()
    }

    suspend fun getUser(id: Int): UserDto {
        return httpClient.get("https://jsonplaceholder.typicode.com/users/$id").body()
    }

    suspend fun getResorts(): ResortRespDto{
        return httpClient.get("https://ski-resorts-and-conditions.p.rapidapi.com/v1/resort"){
                parameter("page", 1)
                header("x-rapidapi-key", BuildConfig.RESORTS_API_KEY)
                header("x-rapidapi-host", "ski-resorts-and-conditions.p.rapidapi.com")
        }.body()
    }
}