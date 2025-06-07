package com.example.composeexample.presentation.state

sealed class UserIntent {
    object LoadUsers : UserIntent()
    object Refresh : UserIntent()
}