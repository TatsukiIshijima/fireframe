package com.tatsuki.fireframe.core.network

import com.tatsuki.fireframe.core.network.model.OneCallResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("onecall")
    suspend fun oneCallCurrent(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("lang") language: String = "ja",
        @Query("exclude") exclude: String = "minutely",
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = BuildConfig.OPEN_WEATHER_API_KEY,
    ): OneCallResponse
}