package com.rahmatsyah.footballleague.ui.main.league

import com.rahmatsyah.footballleague.model.LeagueDetail

interface LeagueView {

    interface Presenter{
        fun requestLeagues()
    }

    interface View{
        fun addLeague(leagueDetail: LeagueDetail)
        fun showLeagues()
        fun failureLoadLeagues()
    }

}