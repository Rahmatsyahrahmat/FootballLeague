package com.rahmatsyah.footballleague.model

import com.google.gson.annotations.SerializedName

data class Match(

    @SerializedName("idEvent")
    val id:String,

    @SerializedName("strEvent")
    val title:String,

    @SerializedName("strSport")
    val sport:String,

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

){
    companion object{

        const val table_name:String = "Match"

        const val id:String = "idEvent"

        const val title:String = "strEvent"

        const val sport:String = "strSport"

        const val date:String = "dateEvent"

        const val homeScore:String = "intHomeScore"

        const val awayScore:String = "intAwayScore"

        const val idHome:String = "idHomeTeam"

        const val idAway:String = "idAwayTeam"

        const val homeShot:String = "intHomeShots"

        const val awayShot:String = "intAwayShots"

        const val homeYellowCard:String = "strHomeYellowCards"

        const val awayYellowCard:String = "strAwayYellowCards"

        const val homeRedCard:String = "strHomeRedCards"

        const val awayRedCard:String = "strAwayRedCards"
    }
}