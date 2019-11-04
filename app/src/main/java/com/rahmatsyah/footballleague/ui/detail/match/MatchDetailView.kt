package com.rahmatsyah.footballleague.ui.detail.match

import com.rahmatsyah.footballleague.model.Match
import com.rahmatsyah.footballleague.model.Team

interface MatchDetailView {
    interface View{
        fun showMatch(match:Match)
        fun showHomeTeam(team:Team)
        fun showAwayTeam(team: Team)
        fun failureLoadMatch()
    }
    interface Presenter{
        fun requestMatch(id:String)
        fun requestAwayTeam(id: String)
        fun requestHomeTeam(id: String)
    }
}