package com.perfectweather.android

import android.app.Application
import android.content.Context

/**
 * @author  perfect
 * @date 2022/12/21 17:03
 *@describe 添加描述
 */
class PerfectWeatherApplication : Application() {
    companion object{
        lateinit var context: Context
        const val TOKEN = "oj25bgoiykc1tuxc"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}