package com.perfectweather.android.logic.network

import com.perfectweather.android.PerfectWeatherApplication
import com.perfectweather.android.logic.model.DailyResponse
import com.perfectweather.android.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author  perfect
 * @date 2022/12/23 10:09
 *@describe 添加描述
 */
interface WeatherService {


    @GET("v2.5/${PerfectWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String) : Call<RealtimeResponse>


    @GET("v2.5/${PerfectWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String):
            Call<DailyResponse>
}