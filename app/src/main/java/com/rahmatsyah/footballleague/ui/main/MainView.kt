package com.rahmatsyah.footballleague.ui.main

import com.rahmatsyah.footballleague.model.LeagueDetail

interface MainView {

    interface Presenter{
        fun requestLeagues()
    }

    interface View{
        fun addLeague(leagueDetail: LeagueDetail)
        fun showLeagues()
        fun failureLoadLeagues()
    }

}