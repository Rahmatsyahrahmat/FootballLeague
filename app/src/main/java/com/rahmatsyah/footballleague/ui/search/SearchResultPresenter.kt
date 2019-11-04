package com.rahmatsyah.footballleague.ui.search

import com.rahmatsyah.footballleague.model.SearchResponse
import com.rahmatsyah.footballleague.service.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultPresenter(private val view:SearchResultView.View):SearchResultView.Presenter {

    override fun requestMatch(query: String) {
        ApiService.searchMatch(query).enqueue(object : Callback<SearchResponse>{
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                view.failureLoadMatch()
            }

            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                response.body()?.matches?.isEmpty().let { view.noMatch() }
                response.body()?.matches?.let { view.showMatch(it) }
            }

        })
    }

}