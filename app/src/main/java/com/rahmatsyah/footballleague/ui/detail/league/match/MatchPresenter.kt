package com.rahmatsyah.footballleague.ui.detail.league.match

import android.annotation.SuppressLint
import android.content.Context
import com.rahmatsyah.footballleague.model.Match
import com.rahmatsyah.footballleague.model.MatchResponse
import com.rahmatsyah.footballleague.service.api.ApiService
import com.rahmatsyah.footballleague.service.db.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MatchPresenter(val context: Context,val view: MatchView.View):
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

    @SuppressLint("SimpleDateFormat")
    override fun requestLastMatch() {
        context.database.use {
            val result = select(Match.table_name)
                .whereArgs("(dateEvent < {date})",
                    "date" to SimpleDateFormat("yyyy-MM-dd").format(Date()))
            val matches = result.parseList(classParser<Match>())
            view.showMatch(matches)
        }
    }

    @SuppressLint("SimpleDateFormat")
    override fun requestNextMatch() {
        context.database.use {
            val result = select(Match.table_name)
                .whereArgs("(dateEvent >= {date})",
                    "date" to SimpleDateFormat("yyyy-MM-dd").format(Date()))
            val matches = result.parseList(classParser<Match>())
            view.showMatch(matches)
        }
    }


}