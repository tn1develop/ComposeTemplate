package com.example.composeexample.presentation.state

import com.example.composeexample.domain.model.User

data class UserState(
    val users: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)