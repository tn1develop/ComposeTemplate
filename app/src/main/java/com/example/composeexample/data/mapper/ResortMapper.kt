package com.example.composeexample.data.mapper

import com.example.composeexample.data.remote.dto.LocationDto
import com.example.composeexample.data.remote.dto.ResortDto
import com.example.composeexample.data.remote.dto.ResortRespDto
import com.example.composeexample.domain.model.Location
import com.example.composeexample.domain.model.Resort

object ResortMapper {
    fun fromResortDto(dto: ResortDto): Resort {
        return Resort(
            name = dto.name,
            country = dto.country,
            url = dto.url,
            location = fromLocationDto( dto.location )
        )
    }

    fun fromLocationDto(dto: LocationDto): Location {
        return Location(
            dto.latitude,
            dto.longitude
        )
    }

    fun fromDtoList(dtoList: ResortRespDto): List<Resort> {
        return dtoList.data.map { fromResortDto(it) }
    }
}