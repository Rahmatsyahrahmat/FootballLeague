package com.rahmatsyah.footballleague.ui.detail.match

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.rahmatsyah.footballleague.R
import com.rahmatsyah.footballleague.model.Match
import com.rahmatsyah.footballleague.model.Team
import kotlinx.android.synthetic.main.activity_match_detail.*

class MatchDetailActivity : AppCompatActivity(), MatchDetailView.View {

    private lateinit var matchDetailPresenter: MatchDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        matchDetailPresenter = MatchDetailPresenter(this)
        intent?.getStringExtra("matchIds")?.let { matchDetailPresenter.requestMatch(it) }

    }

    @SuppressLint("SetTextI18n")
    override fun showMatch(match: Match) {
        tvTitle.text = match.title
        tvDate.text = match.date
        if (match.homeScore!=null&&match.awayScore!=null){
            tvFt.text = "FT"
            tvScore.text = match.homeScore+" : "+match.awayScore
            tvHomeScore.text = match.homeScore
            tvAwayScore.text = match.awayScore
            tvHomeShot.text = match.homeShot.toString()
            tvAwayShot.text = match.awayShot.toString()
            tvHomeYellowCard.text = (match.homeYellowCard.split(";").size-1).toString()
            tvAwayYellowCard.text = (match.awayYellowCard.split(";").size-1).toString()
            tvHomeRedCard.text = (match.homeRedCard.split(";").size-1).toString()
            tvAwayRedCard.text = (match.awayRedCard.split(";").size-1).toString()
        }
        tvHome.text = match.title.split(" vs ")[0]
        tvAway.text = match.title.split(" vs ")[1]

        matchDetailPresenter.requestHomeTeam(match.idHome)
        matchDetailPresenter.requestAwayTeam(match.idAway)
    }

    override fun showHomeTeam(team: Team) {
        ivHome.let { Glide.with(this).load(team.badge).into(it) }
    }

    override fun showAwayTeam(team: Team) {
        ivAway.let { Glide.with(this).load(team.badge).into(it) }
    }

    override fun failureLoadMatch() {
        Toast.makeText(this,"Failure to load match",Toast.LENGTH_LONG).show()
    }
}
