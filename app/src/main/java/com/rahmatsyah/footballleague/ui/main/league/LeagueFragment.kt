package com.rahmatsyah.footballleague.ui.main.league


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.rahmatsyah.footballleague.R
import com.rahmatsyah.footballleague.model.LeagueDetail
import kotlinx.android.synthetic.main.fragment_league.*

/**
 * A simple [Fragment] subclass.
 */
class LeagueFragment : Fragment(), LeagueView.View {

    private val leaguesDetail:MutableList<LeagueDetail> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_league, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val leaguePresenter = LeaguePresenter(this)
        leaguePresenter.requestLeagues()

    }

    override fun showLeagues() {
        leaguesProgress?.visibility = View.GONE
        listLeagues?.layoutManager = LinearLayoutManager(context)
        listLeagues?.recycledViewPool?.setMaxRecycledViews(0,0)
        listLeagues?.adapter =
            context?.let { LeaguesAdapter(it, leaguesDetail) }
    }

    override fun failureLoadLeagues() {
        context?.let { Toast.makeText(it,"Failure to load leagues", Toast.LENGTH_SHORT).show() }
    }

    override fun addLeague(leagueDetail: LeagueDetail) {
        leaguesDetail.add(leagueDetail)
    }




}
