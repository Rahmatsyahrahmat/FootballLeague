package com.rahmatsyah.footballleague.service.api

import com.rahmatsyah.footballleague.model.LeagueDetailResponse
import com.rahmatsyah.footballleague.model.LeagueResponse
import com.rahmatsyah.footballleague.model.MatchResponse
import com.rahmatsyah.footballleague.model.TeamResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiService {

    @GET("all_leagues.php/")
    fun getAllLeagues():Call<LeagueResponse>

    @GET("lookupleague.php")
    fun getLeaguesDetail(@Query("id") id:String):Call<LeagueDetailResponse>

    @GET("search_all_teams.php")
    fun getTeamByLeague(@Query("l") league:String):Call<TeamResponse>

    @GET("eventspastleague.php")
    fun getLastLeagueMatch(@Query("id") id:String):Call<MatchResponse>

    @GET("eventsnextleague.php")
    fun getNextLeagueMatch(@Query("id") id:String):Call<MatchResponse>

    @GET("lookupteam.php")
    fun getTeamById(@Query("id")id:String):Call<TeamResponse>

    @GET("lookupevent.php")
    fun getDetailMatch(@Query("id") id:String): Call<MatchResponse>

}