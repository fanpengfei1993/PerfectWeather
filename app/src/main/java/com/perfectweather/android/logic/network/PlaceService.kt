package com.perfectweather.android.logic.network

import com.perfectweather.android.PerfectWeatherApplication
import com.perfectweather.android.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author  perfect
 * @date 2022/12/21 17:11
 *@describe 添加描述
 */
interface PlaceService {

    //实时天气数据 API:  https://api.caiyunapp.com/v2.5/{Token}/{经度, 纬度}/realtime.json
    //https://api.caiyunapp.com/v2.6/TAkhjf8d1nlSlspN/101.6656,39.2072/realtime"
    //Response{protocol=http/1.1, code=404, message=NOT FOUND,
    // url=https://api.caiyunapp.com/v2.6/?token=oj25bgoiykc1tuxc/101.6656,39.2072/realtime&query=gr}
//    @GET("v2.6/?token=${PerfectWeatherApplication.TOKEN}/&lang=zh_CN")
    //https://api.caiyunapp.com/v2.5/{token}/101.6656,39.2072/realtime
    //https://api.caiyunapp.com/v2.5/oj25bgoiykc1tuxc/121.6544,25.1552/realtime.json
    @GET("v2/place?token=${PerfectWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}