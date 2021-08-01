package com.example.androidonkotlin.utils

import com.example.androidonkotlin.model.FactDTO
import com.example.androidonkotlin.model.Weather
import com.example.androidonkotlin.model.WeatherDTO
import com.example.androidonkotlin.model.getDefaultCity

fun convertDtoToModel(weatherDTO: WeatherDTO): List<Weather> {
    val fact: FactDTO = weatherDTO.fact!!
    return listOf(
        Weather(
            getDefaultCity(), fact.temp!!, fact.feels_like!!, fact.condition!!,
            fact.icon
        )
    )
}
