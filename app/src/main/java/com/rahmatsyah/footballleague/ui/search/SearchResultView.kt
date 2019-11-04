package com.rahmatsyah.footballleague.ui.search

import com.rahmatsyah.footballleague.model.Match

interface SearchResultView {
    interface View{
        fun showMatch(matches:List<Match>)
        fun failureLoadMatch()
        fun noMatch()
    }
    interface Presenter{
        fun requestMatch(query:String)
    }
}