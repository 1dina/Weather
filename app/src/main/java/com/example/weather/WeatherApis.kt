package com.example.weather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApis {
    @GET("data/2.5/weather")
    fun getWeatherInfo(
        @Query("q") country: String,
        @Query("appid") appId: String
    ): Call<MainActivity.WeatherInfoData>

    @GET("data/2.5/weather")
    fun getWeatherInfo2(
        @Query("q") country: String,
        @Query("appid") appId: String
    ): Call<MainActivity.WeatherInfoData2>

}