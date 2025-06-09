package com.example.composeexample.di

import com.example.composeexample.data.remote.HttpClient
import com.example.composeexample.data.remote.api.ApiService
import com.example.composeexample.data.repository.ResortRepositoryImpl
import com.example.composeexample.data.repository.UserRepositoryImpl
import com.example.composeexample.domain.repository.ResortRepository
import com.example.composeexample.domain.repository.UserRepository
import com.example.composeexample.domain.usecase.GetResortsUseCase
import com.example.composeexample.domain.usecase.GetUsersUseCase
import com.example.composeexample.presentation.viewmodel.ResortsViewModel
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
    single<ResortRepository> { ResortRepositoryImpl(get()) }

    // Use Cases
    single { GetUsersUseCase(get()) }
    single { GetResortsUseCase(get()) }

    // ViewModels
    viewModel { UserViewModel(get()) }
    viewModel { ResortsViewModel(get()) }
}