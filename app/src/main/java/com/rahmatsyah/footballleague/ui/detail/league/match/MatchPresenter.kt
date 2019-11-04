package com.rahmatsyah.footballleague.ui.detail.league.match

import com.rahmatsyah.footballleague.model.MatchResponse
import com.rahmatsyah.footballleague.service.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchPresenter(val view: MatchView.View):
    MatchView.Presenter {

    override fun requestNextMatch(id: String) {
        ApiService.getNextLeagueMatch(id).enqueue(object: Callback<MatchResponse> {
            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                view.failureLoadMatch()
            }

            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                response.body()?.matches?.let { view.showMatch(it) }
            }

        })
    }

    override fun requestLastMatch(id: String) {
        ApiService.getLastLeagueMatch(id).enqueue(object: Callback<MatchResponse> {
            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                view.failureLoadMatch()
            }

            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                response.body()?.matches?.let { view.showMatch(it) }
            }

        })
    }

}