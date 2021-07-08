package com.example.androidonkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidonkotlin.model.Repository
import com.example.androidonkotlin.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl()
) : ViewModel() {

    fun getLiveData() = liveDataToObserve

    fun getWeatherFromLocalSource() = getDataFromLocalSource()

    fun getWeatherFromRemoteSource() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1500)
            liveDataToObserve.postValue(AppState.Success(repositoryImpl.getWeatherFromLocalStorage()))
        }.start()
    }
}