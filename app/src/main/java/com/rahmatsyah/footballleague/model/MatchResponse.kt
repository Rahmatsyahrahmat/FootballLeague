package com.rahmatsyah.footballleague.model

import com.google.gson.annotations.SerializedName

data class MatchResponse(
    @SerializedName("events")
    val matches:List<Match>
)