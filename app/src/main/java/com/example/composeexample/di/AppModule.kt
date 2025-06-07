package com.example.composeexample.di

import com.example.composeexample.data.remote.HttpClient
import com.example.composeexample.data.remote.api.ApiService
import com.example.composeexample.data.repository.UserRepositoryImpl
import com.example.composeexample.domain.repository.UserRepository
import com.example.composeexample.domain.usecase.GetUsersUseCase
import com.example.composeexample.presentation.viewmodel.UserViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // HTTP Client
    single { HttpClient.client }

    // API Service
    single { ApiService(get()) }

    // Repository
    single<UserRepository> { UserRepositoryImpl(get()) }

    // Use Cases
    single { GetUsersUseCase(get()) }

    // ViewModels
    viewModel { UserViewModel(get()) }
}