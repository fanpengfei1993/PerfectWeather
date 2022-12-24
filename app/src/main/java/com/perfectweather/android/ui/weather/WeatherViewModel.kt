package com.perfectweather.android.ui.weather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.perfectweather.android.logic.Repository
import com.perfectweather.android.logic.model.Location

/**
 * @author  perfect
 * @date 2022/12/23 10:34
 *@describe 添加描述
 */
class WeatherViewModel :ViewModel() {
    private val locationLiveData = MutableLiveData<Location>()

    var locationLng = ""
    var locationLat = ""
    var placeName = ""

    val weatherLiveData = Transformations.switchMap(locationLiveData){

        Log.e("WeatherViewModel", ": it.lng "+it.lng)
        Log.e("WeatherViewModel", ": it.lat "+it.lat)
        Repository.refreshWeather1(it.lng,it.lat)
    }

    fun refreshWeather(lng: String, lat: String){
        locationLiveData.value = Location(lng,lat)
    }

}