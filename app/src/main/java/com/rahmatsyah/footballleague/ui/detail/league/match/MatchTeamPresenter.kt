package com.rahmatsyah.footballleague.ui.detail.league.match

import com.rahmatsyah.footballleague.model.TeamResponse
import com.rahmatsyah.footballleague.service.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchTeamPresenter(val teamView:MatchTeamView.View):MatchTeamView.Presenter{

    override fun requestAwayTeam(id: String) {
        ApiService.getTeamById(id).enqueue(object : Callback<TeamResponse>{
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                response.body()?.teams?.get(0)?.let { teamView.showAwayTeam(it) }
            }

        })
    }

    override fun requestHomeTeam(id: String) {
        ApiService.getTeamById(id).enqueue(object : Callback<TeamResponse>{
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                response.body()?.teams?.get(0)?.let { teamView.showHomeTeam(it) }
            }

        })
    }

}