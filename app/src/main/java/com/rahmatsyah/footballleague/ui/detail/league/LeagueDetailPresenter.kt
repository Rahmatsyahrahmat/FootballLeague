package com.rahmatsyah.footballleague.ui.detail.league

import com.rahmatsyah.footballleague.model.LeagueDetailResponse
import com.rahmatsyah.footballleague.model.TeamResponse
import com.rahmatsyah.footballleague.service.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeagueDetailPresenter(val view: LeagueDetailView.View):
    LeagueDetailView.Presenter {
    
    override fun requestLeagueDetail(id: String) {
        ApiService.getLeaguesDetail(id).enqueue(object : Callback<LeagueDetailResponse>{
            override fun onFailure(call: Call<LeagueDetailResponse>, t: Throwable) {
                view.failureLoadData("Failure to get league data")
            }

            override fun onResponse(call: Call<LeagueDetailResponse>, response: Response<LeagueDetailResponse>) {
                response.body()?.league?.get(0)?.let { view.showLeagueDetail(it) }
            }

        })
    }

    override fun requestLeagueTeams(league: String) {
        ApiService.getTeamByLeague(league).enqueue(object: Callback<TeamResponse>{
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                view.failureLoadData("Failure to get teams data")
            }

            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                response.body()?.teams?.let { view.showLeagueTeams(it) }
            }

        })
    }
    
}