package com.example.androidonkotlin.repository

import com.example.androidonkotlin.model.Weather
import com.example.androidonkotlin.room.HistoryDao
import com.example.androidonkotlin.utils.convertHistoryEntityToWeather
import com.example.androidonkotlin.utils.convertWeatherToEntity

class LocalRepositoryImpl(private val localDataSource: HistoryDao) : LocalRepository {
    override fun getAllHistory(): List<Weather> {
        return convertHistoryEntityToWeather(localDataSource.all())
    }

    override fun saveEntity(weather: Weather) {
        localDataSource.insert(convertWeatherToEntity(weather))
    }
}