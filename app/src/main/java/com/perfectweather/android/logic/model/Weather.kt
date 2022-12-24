package com.perfectweather.android.logic.model

/**
 * @author  perfect
 * @date 2022/12/23 10:07
 *@describe 添加描述
 */
data class Weather(val realtime: RealtimeResponse.Realtime,val daily: DailyResponse.Daily) {
}