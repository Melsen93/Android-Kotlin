package com.example.androidonkotlin.repository

import com.example.androidonkotlin.model.Weather

interface LocalRepository {
    fun getAllHistory(): List<Weather>
    fun saveEntity(weather: Weather)
}