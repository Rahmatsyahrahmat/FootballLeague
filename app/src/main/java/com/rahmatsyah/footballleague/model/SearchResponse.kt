package com.rahmatsyah.footballleague.model

import com.google.gson.annotations.SerializedName

data class SearchResponse (
    @SerializedName("event")
    val matches:List<Match>
)