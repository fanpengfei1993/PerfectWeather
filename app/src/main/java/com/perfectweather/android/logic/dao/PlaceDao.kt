package com.perfectweather.android.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.perfectweather.android.PerfectWeatherApplication
import com.perfectweather.android.logic.model.Place

/**
 * @author  perfect
 * @date 2022/12/23 15:04
 *@describe 添加描述
 */
object PlaceDao {
    fun savePlace(place: Place){
        sharedPreferences().edit { putString("place",Gson().toJson(place)) }
    }

    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place","")
        return Gson().fromJson(placeJson,Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")




    private fun sharedPreferences() = PerfectWeatherApplication.context
        .getSharedPreferences("perfect_weather",Context.MODE_PRIVATE)

}