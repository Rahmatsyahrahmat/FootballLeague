package com.rahmatsyah.footballleague.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahmatsyah.footballleague.R
import com.rahmatsyah.footballleague.model.LeagueDetail
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),MainView.View {

    private val leaguesDetail:MutableList<LeagueDetail> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainPresenter = MainPresenter(this)
        mainPresenter.requestLeagues()

    }

    override fun showLeagues() {
        leaguesProgress.visibility = View.GONE
        listLeagues.layoutManager = LinearLayoutManager(this)
        listLeagues.recycledViewPool.setMaxRecycledViews(0,0)
        listLeagues.adapter =
            LeaguesAdapter(this, leaguesDetail)
    }

    override fun failureLoadLeagues() {
        Toast.makeText(this,"Failure to load leagues",Toast.LENGTH_SHORT).show()
    }

    override fun addLeague(leagueDetail: LeagueDetail) {
        leaguesDetail.add(leagueDetail)
    }

}
