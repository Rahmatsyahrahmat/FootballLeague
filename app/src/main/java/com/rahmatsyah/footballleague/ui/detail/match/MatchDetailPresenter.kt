package com.rahmatsyah.footballleague.ui.detail.match

import com.rahmatsyah.footballleague.model.MatchResponse
import com.rahmatsyah.footballleague.model.TeamResponse
import com.rahmatsyah.footballleague.service.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchDetailPresenter(val view:MatchDetailView.View):MatchDetailView.Presenter {

    override fun requestMatch(id: String) {
        ApiService.getDetailMatch(id).enqueue(object :Callback<MatchResponse>{
            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                view.failureLoadMatch()
            }

            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                response.body()?.matches?.get(0)?.let { view.showMatch(it) }
            }

        })
    }

    override fun requestAwayTeam(id: String) {
        ApiService.getTeamById(id).enqueue(object : Callback<TeamResponse> {
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                view.failureLoadMatch()
            }

            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                response.body()?.teams?.get(0)?.let { view.showAwayTeam(it) }
            }

        })
    }

    override fun requestHomeTeam(id: String) {
        ApiService.getTeamById(id).enqueue(object : Callback<TeamResponse> {
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                view.failureLoadMatch()
            }

            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                response.body()?.teams?.get(0)?.let { view.showHomeTeam(it) }
            }

        })
    }
}