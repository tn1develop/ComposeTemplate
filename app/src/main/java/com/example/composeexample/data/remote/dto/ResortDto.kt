package com.example.composeexample.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ResortDto(
    val name: String,
    val country: String,
    val url: String,
    val location: LocationDto,
)
