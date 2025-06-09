package com.example.composeexample.data.repository

import com.example.composeexample.data.mapper.ResortMapper
import com.example.composeexample.data.remote.api.ApiService
import com.example.composeexample.domain.model.Resort
import com.example.composeexample.domain.repository.ResortRepository

class ResortRepositoryImpl(
    private val apiService: ApiService
) : ResortRepository {

    override suspend fun getResorts(): Result<List<Resort>> {
        return try {
            val resortsDto = apiService.getResorts()
            val users = ResortMapper.fromDtoList(resortsDto)
            Result.success(users)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}