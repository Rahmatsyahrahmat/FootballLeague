package com.rahmatsyah.footballleague.service.api

import com.rahmatsyah.footballleague.BuildConfig
import com.rahmatsyah.footballleague.model.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService{

    private var service:IApiService

    init {
        val retrofit:Retrofit = createAdapter()
        service = retrofit.create(IApiService::class.java)
    }

    private fun createAdapter():Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getAllLeagues(): Call<LeagueResponse> = service.getAllLeagues()

    fun getLeaguesDetail(id:String): Call<LeagueDetailResponse> = service.getLeaguesDetail(id)

    fun getTeamByLeague(league:String): Call<TeamResponse> = service.getTeamByLeague(league)

    fun getLastLeagueMatch(id:String):Call<MatchResponse> = service.getLastLeagueMatch(id)

    fun getNextLeagueMatch(id:String):Call<MatchResponse> = service.getNextLeagueMatch(id)

    fun getTeamById(id: String):Call<TeamResponse> = service.getTeamById(id)

    fun getDetailMatch(id: String): Call<MatchResponse> = service.getDetailMatch(id)

    fun searchMatch(e:String): Call<SearchResponse> = service.searchMatch(e)

}