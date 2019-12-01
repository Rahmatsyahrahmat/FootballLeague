package com.rahmatsyah.footballleague.ui.detail.league

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rahmatsyah.footballleague.ui.detail.league.match.last.LastMatchFragment
import com.rahmatsyah.footballleague.ui.detail.league.match.next.NextMatchFragment

class MatchPagerAdapter(fm:FragmentManager,leagueIds:String?): FragmentPagerAdapter(fm) {

    private val pages: List<Fragment> = listOf(
        LastMatchFragment(),
        NextMatchFragment()
    )

    init {
        val bundle = Bundle()
        if (leagueIds!=null) {
            bundle.putString("leagueIds", leagueIds)
            pages[0].arguments = bundle
            pages[1].arguments = bundle
        }
    }

    override fun getItem(position: Int): Fragment = pages[position]

    override fun getCount(): Int = pages.size

}