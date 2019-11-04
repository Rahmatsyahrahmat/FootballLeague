package com.rahmatsyah.footballleague.model

import com.google.gson.annotations.SerializedName

data class League (

    @SerializedName("idLeague")
    val idLeague:String,

    @SerializedName("strSport")
    val strSport:String

)