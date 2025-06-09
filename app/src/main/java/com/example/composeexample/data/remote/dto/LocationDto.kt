package com.example.composeexample.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationDto (
    val latitude: String,
    val longitude: String,
)