package com.rahmatsyah.footballleague.ui.detail.league.match

import com.rahmatsyah.footballleague.model.Match

interface MatchView {
    interface View{
        fun showMatch(matches:List<Match>)
        fun failureLoadMatch()
    }
    interface Presenter{
        fun requestLastMatch(id:String)
        fun requestNextMatch(id:String)
        fun requestLastMatch()
        fun requestNextMatch()
    }
}