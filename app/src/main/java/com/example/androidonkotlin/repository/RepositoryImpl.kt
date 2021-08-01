package com.example.androidonkotlin.repository

import com.example.androidonkotlin.model.Weather
import com.example.androidonkotlin.model.getRussianCities
import com.example.androidonkotlin.model.getWorldCities

class RepositoryImpl : Repository {
    override fun getWeatherFromServer() = Weather()

    override fun getWeatherFromLocalStorageRus() = getRussianCities()

    override fun getWeatherFromLocalStorageWorld() = getWorldCities()
}