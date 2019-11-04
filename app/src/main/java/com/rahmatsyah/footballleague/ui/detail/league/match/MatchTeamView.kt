package com.rahmatsyah.footballleague.ui.detail.league.match

import com.rahmatsyah.footballleague.model.Team

interface MatchTeamView {
    interface View{
        fun showHomeTeam(team: Team)
        fun showAwayTeam(team: Team)
    }
    interface Presenter{
        fun requestHomeTeam(id:String)
        fun requestAwayTeam(id:String)
    }
}