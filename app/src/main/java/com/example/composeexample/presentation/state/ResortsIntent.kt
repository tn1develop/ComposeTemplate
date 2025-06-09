package com.example.composeexample.presentation.state

sealed class ResortsIntent {
    object LoadResorts : ResortsIntent()
    object Refresh : ResortsIntent()
}