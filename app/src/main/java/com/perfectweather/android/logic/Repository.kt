package com.perfectweather.android.logic

import android.view.KeyEvent.DispatcherState
import androidx.lifecycle.liveData
import com.perfectweather.android.logic.dao.PlaceDao
import com.perfectweather.android.logic.model.Place
import com.perfectweather.android.logic.model.Weather
import com.perfectweather.android.logic.network.PerfectWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.sql.ResultSet
import kotlin.coroutines.CoroutineContext

/**
 * @author  perfect
 * @date 2022/12/22 9:44
 *@describe 添加描述
 */
object Repository {

    fun searchPlaces(query: String) = liveData(Dispatchers.IO){
        val result = try {
            val placeResponse = PerfectWeatherNetwork.searchPlaces(query)
            if(placeResponse.status == "ok"){
                val place = placeResponse.places
                Result.success(place)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.status }"))
            }
        }catch (e: Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
    fun searchPlaces1(query: String) = fire(Dispatchers.IO){
        val placeResponse = PerfectWeatherNetwork.searchPlaces(query)
        if(placeResponse.status == "ok"){
            val place = placeResponse.places
            Result.success(place)
        }else{
            Result.failure(RuntimeException("response status is ${placeResponse.status }"))
        }
    }

    fun refreshWeather(lng: String, lat: String) = liveData(Dispatchers.IO) {
        val result = try {
            coroutineScope {
                val deferredRealtime = async {
                    PerfectWeatherNetwork.getRealtimeWeather(lng, lat)
                }
                val deferredDaily = async {
                    PerfectWeatherNetwork.getDailyWeather(lng, lat)
                }
                val  realtimeResponse = deferredRealtime.await()
                val dailyResponse = deferredDaily.await()
                if(realtimeResponse.status == "ok" && dailyResponse.status == "ok"){
                    val weather =
                        Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                    Result.success(weather)
                }else{
                    Result.failure(RuntimeException(
                        "realtime response status is ${realtimeResponse.status}" +
                                "daily response status is ${dailyResponse.status}"
                    ))
                }
            }
        }catch (e: Exception){
            Result.failure<Weather>(e)
        }
        emit(result)

    }

    fun refreshWeather1(lng: String, lat: String) = fire(Dispatchers.IO){
        coroutineScope {
            val deferredRealtime = async {
                PerfectWeatherNetwork.getRealtimeWeather(lng, lat)
            }
            val deferredDaily = async {
                PerfectWeatherNetwork.getDailyWeather(lng, lat)
            }
            val  realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            if(realtimeResponse.status == "ok" && dailyResponse.status == "ok"){
                val weather =
                    Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                Result.success(weather)
            }else{
                Result.failure(RuntimeException(
                    "realtime response status is ${realtimeResponse.status}" +
                            "daily response status is ${dailyResponse.status}"
                ))
            }
        }

    }





        private fun <T> fire(context : CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            }catch (e: Exception){
                Result.failure<T>(e)
            }
            emit(result)
        }

    fun savePlace(place: Place) = PlaceDao.savePlace(place)

    fun getSavedPlace() = PlaceDao.getSavedPlace()

    fun isPlaceSaved() = PlaceDao.isPlaceSaved()



}