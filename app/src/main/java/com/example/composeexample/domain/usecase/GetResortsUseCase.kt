package com.example.composeexample.domain.usecase

import com.example.composeexample.domain.model.Resort
import com.example.composeexample.domain.model.User
import com.example.composeexample.domain.repository.ResortRepository

class GetResortsUseCase(
    private val resortRepo: ResortRepository
) {
    suspend operator fun invoke(): Result<List<Resort>> {
        return resortRepo.getResorts()
    }
}