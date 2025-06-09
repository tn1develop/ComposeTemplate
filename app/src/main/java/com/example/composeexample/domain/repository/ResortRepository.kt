package com.example.composeexample.domain.repository

import com.example.composeexample.domain.model.Resort
import com.example.composeexample.domain.model.User

interface ResortRepository {
    suspend fun getResorts(): Result<List<Resort>>
}