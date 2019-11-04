package com.rahmatsyah.footballleague.ui.detail.league

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.rahmatsyah.footballleague.R
import com.rahmatsyah.footballleague.model.LeagueDetail
import com.rahmatsyah.footballleague.model.Team
import kotlinx.android.synthetic.main.activity_league_detail.*
import org.jetbrains.anko.sp


class LeagueDetailActivity : AppCompatActivity(), LeagueDetailView.View {

    private lateinit var leagueDetailPresenter:LeagueDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_detail)

        leagueDetailPresenter = LeagueDetailPresenter(this)
        intent.extras?.getString("leagueIds")?.let { leagueDetailPresenter.requestLeagueDetail(it) }

        vpLeagueDetail.adapter =
            intent.extras?.getString("leagueIds")?.let {
                MatchPagerAdapter(supportFragmentManager, it)
            }

        tlLeagueDetail.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                vpLeagueDetail.currentItem = p0?.position ?: 0
            }

        })

        vpLeagueDetail.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tlLeagueDetail))

    }

    override fun showLeagueDetail(leagueDetail: LeagueDetail) {
        ivLeagueBadge.let { Glide.with(this).load(leagueDetail.badge).into(it) }
        tvLeagueName.text = leagueDetail.strLeague
        tvLeagueDescription.text = leagueDetail.description
        tvLeagueDescription.setTextSize(sp(15).toFloat())
        leagueDetailPresenter.requestLeagueTeams(leagueDetail.strLeague)
    }

    override fun showLeagueTeams(teams: List<Team>) {
        rvLeagueTeams.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rvLeagueTeams.adapter = TeamAdapter(this,teams)
    }

    override fun failureLoadData(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu,menu)

        val searchManager:SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        (menu?.findItem(R.id.action_search)?.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        return true
    }

}
