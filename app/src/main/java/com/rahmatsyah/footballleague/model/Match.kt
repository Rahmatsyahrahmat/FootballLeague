package com.rahmatsyah.footballleague.model

import com.google.gson.annotations.SerializedName

data class Match(

    @SerializedName("idEvent")
    val id:String,

    @SerializedName("strEvent")
    val title:String,

    @SerializedName("dateEvent")
    val date:String,

    @SerializedName("intHomeScore")
    val homeScore:String?,

    @SerializedName("intAwayScore")
    val awayScore:String?,

    @SerializedName("idHomeTeam")
    val idHome:String,

    @SerializedName("idAwayTeam")
    val idAway:String,

    @SerializedName("intHomeShots")
    val homeShot:Int,

    @SerializedName("intAwayShots")
    val awayShot:Int,

    @SerializedName("strHomeYellowCards")
    val homeYellowCard:String,

    @SerializedName("strAwayYellowCards")
    val awayYellowCard:String,

    @SerializedName("strHomeRedCards")
    val homeRedCard:String,

    @SerializedName("strAwayRedCards")
    val awayRedCard:String

)