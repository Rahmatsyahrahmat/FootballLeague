package com.rahmatsyah.footballleague.model

import com.google.gson.annotations.SerializedName

data class LeagueDetailResponse(
    @SerializedName("leagues")
    val league:List<LeagueDetail>
)