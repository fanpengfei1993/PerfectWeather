package com.perfectweather.android.logic.model

import com.google.gson.annotations.SerializedName

/**
 * @author  perfect
 * @date 2022/12/23 10:03
 *@describe 添加描述
 */
data class RealtimeResponse(val status: String, val result: Result) {

    data class Result(val realtime: Realtime)
    data class Realtime(val skycon: String, val temperature: Float,
                        @SerializedName("air_quality") val airQuality: AirQuality)
    data class AirQuality(val aqi: AQI)
    data class AQI(val chn: Float)
}