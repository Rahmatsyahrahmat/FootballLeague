package com.rahmatsyah.footballleague.ui.detail.league

import com.rahmatsyah.footballleague.model.LeagueDetail
import com.rahmatsyah.footballleague.model.Team

interface LeagueDetailView {

    interface View{
        fun showLeagueDetail(leagueDetail:LeagueDetail)
        fun showLeagueTeams(teams:List<Team>)
        fun failureLoadData(msg:String)
    }

    interface Presenter{
        fun requestLeagueDetail(id:String)
        fun requestLeagueTeams(league:String)
    }

}