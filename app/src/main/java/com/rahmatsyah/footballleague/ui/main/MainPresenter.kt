package com.rahmatsyah.footballleague.ui.main

import com.rahmatsyah.footballleague.model.*
import com.rahmatsyah.footballleague.service.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(val view:MainView.View) : MainView.Presenter {


    override fun requestLeagues() {
        ApiService.getAllLeagues().enqueue(object : Callback<LeagueResponse> {
            override fun onFailure(call: Call<LeagueResponse>, t: Throwable) {
                view.failureLoadLeagues()
            }

            override fun onResponse(call: Call<LeagueResponse>, response: Response<LeagueResponse>
            ) {
                val leagues:MutableList<League> = mutableListOf()
                response.body()?.leagues?.forEach {
                    if (it.strSport == "Soccer") {
                        leagues.add(it)
                    }
                }
                requestLeaguesDetail(leagues)
            }

        })
    }

    private fun requestLeaguesDetail(leagues:List<League>) {
        leagues.forEachIndexed { index, league ->
            ApiService.getLeaguesDetail(league.idLeague).enqueue(object :Callback<LeagueDetailResponse>{
                override fun onFailure(call: Call<LeagueDetailResponse>, t: Throwable) {
                    view.failureLoadLeagues()
                }

                override fun onResponse(call: Call<LeagueDetailResponse>, response: Response<LeagueDetailResponse>
                ) {
                    response.body()?.league?.get(0)?.let { view.addLeague(it) }
                    if (index==leagues.size-1) view.showLeagues()
                }

            })

        }

    }

}