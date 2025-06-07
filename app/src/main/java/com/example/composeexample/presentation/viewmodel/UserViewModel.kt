package com.example.composeexample.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeexample.domain.usecase.GetUsersUseCase
import com.example.composeexample.presentation.state.UserIntent
import com.example.composeexample.presentation.state.UserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UserState())
    val state: StateFlow<UserState> = _state.asStateFlow()

    fun handleIntent(intent: UserIntent) {
        when (intent) {
            is UserIntent.LoadUsers -> loadUsers()
            is UserIntent.Refresh -> loadUsers()
        }
    }

    private fun loadUsers() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            getUsersUseCase().fold(
                onSuccess = { users ->
                    _state.value = _state.value.copy(
                        users = users,
                        isLoading = false,
                        error = null
                    )
                },
                onFailure = { exception ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = exception.message
                    )
                }
            )
        }
    }
}