package com.example.composeexample.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
class ResortRespDto (
    val page: Int,
    val data: List<ResortDto>
)