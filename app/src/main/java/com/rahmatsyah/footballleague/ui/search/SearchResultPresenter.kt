package com.rahmatsyah.footballleague.ui.search

import com.rahmatsyah.footballleague.model.Match
import com.rahmatsyah.footballleague.model.SearchResponse
import com.rahmatsyah.footballleague.service.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultPresenter(private val view:SearchResultView.View):SearchResultView.Presenter {

    override fun requestMatch(query: String?) {
        query?.let {
            ApiService.searchMatch(it).enqueue(object : Callback<SearchResponse>{
                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    view.failureLoadMatch()
                }

                override fun onResponse(call: Call<SearchResponse>,  response: Response<SearchResponse>) {
                    response.body()?.matches?.isEmpty()?.equals(true).let { view.noMatch() }
                    val matches:MutableList<Match> = mutableListOf()
                    response.body()?.matches?.forEach {match->
                        if (match.sport == "Soccer") {
                            matches.add(match)
                        }
                    }
                    view.showMatch(matches)
                }

            })
        }
    }

}