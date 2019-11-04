package com.rahmatsyah.footballleague.model

import com.google.gson.annotations.SerializedName

data class Team (
    @SerializedName("idTeam")
    val idTeam:String,
    @SerializedName("strTeam")
    val name:String,
    @SerializedName("strTeamBadge")
    val badge:String
    )