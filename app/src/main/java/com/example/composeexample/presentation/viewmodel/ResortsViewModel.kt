package com.example.composeexample.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeexample.domain.usecase.GetResortsUseCase
import com.example.composeexample.presentation.state.ResortsIntent
import com.example.composeexample.presentation.state.ResortsState
import com.example.composeexample.presentation.state.UserIntent
import com.example.composeexample.presentation.state.UserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ResortsViewModel(
    private val getResortsUseCase: GetResortsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ResortsState())
    val state: StateFlow<ResortsState> = _state.asStateFlow()

    fun handleIntent(intent: ResortsIntent) {
        when (intent) {
            is ResortsIntent.LoadResorts -> loadResorts()
            is ResortsIntent.Refresh -> loadResorts()
        }
    }

    private fun loadResorts() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            getResortsUseCase().fold(
                onSuccess = { resorts ->
                    _state.value = _state.value.copy(
                        resorts = resorts,
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