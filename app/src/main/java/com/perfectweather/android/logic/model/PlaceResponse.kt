package com.perfectweather.android.logic.model

import com.google.gson.annotations.SerializedName

/**
 * @author  perfect
 * @date 2022/12/21 17:06
 *@describe 添加描述
 */
data class PlaceResponse(val status: String, val places: List<Place>)

data class Place(val name: String, val location: Location,
                 @SerializedName("formatted_address") val address: String)

data class Location(val lng: String, val lat: String)
