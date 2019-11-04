package com.rahmatsyah.footballleague.model

import com.google.gson.annotations.SerializedName

data class LeagueDetail(

    @SerializedName("idLeague")
    val idLeague:String,

    @SerializedName("strLeague")
    val strLeague:String,

    @SerializedName("strBadge")
    var badge:String?="",

    @SerializedName("strDescriptionEN")
    var description:String

)