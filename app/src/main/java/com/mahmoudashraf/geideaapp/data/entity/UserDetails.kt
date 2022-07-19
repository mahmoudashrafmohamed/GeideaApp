package com.mahmoudashraf.geideaapp.data.entity
import com.google.gson.annotations.SerializedName

data class UserDetails(
    @SerializedName("data")
    val `data`: User,
    @SerializedName("support")
    val support: Support
)
