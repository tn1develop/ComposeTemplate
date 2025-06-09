package com.example.composeexample.presentation.state

import com.example.composeexample.domain.model.Resort

data class ResortsState(
    val resorts: List<Resort> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)