package com.rahmatsyah.footballleague.ui.detail.match

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.rahmatsyah.footballleague.R
import com.rahmatsyah.footballleague.model.Match
import com.rahmatsyah.footballleague.model.Team
import com.rahmatsyah.footballleague.service.db.database
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class MatchDetailActivity : AppCompatActivity(), MatchDetailView.View{

    private lateinit var matchDetailPresenter: MatchDetailPresenter

    private lateinit var matchId:String
    private var isFavorite:Boolean = false

    private lateinit var match:Match

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        matchDetailPresenter = MatchDetailPresenter(this)
        matchId = intent.getStringExtra("matchIds")!!
        matchDetailPresenter.requestMatch(matchId)


    }

    @SuppressLint("SetTextI18n")
    override fun showMatch(match: Match) {
        this.match = match
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite,menu)

        database.use{
            val result = select(Match.table_name)
                .whereArgs("(idEvent = {idEvent})",
                    Match.id to matchId)
            val matches = result.parseList(classParser<Match>())
            if (matches.isNotEmpty()) isFavorite = true

        }

        if (isFavorite)
            menu?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white)
        else
            menu?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuF_favorite -> {
                if (isFavorite)
                    removeFromFavorite()
                else
                    addToFavorite()

                isFavorite = !isFavorite

                if (isFavorite)
                    item.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white)
                else
                    item.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white)

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){

        database.use {
            insert(Match.table_name,
                Match.id to match.id,
                Match.title to match.title,
                Match.sport to match.sport,
                Match.date to match.date,
                Match.homeScore to match.homeScore,
                Match.awayScore to match.awayScore,
                Match.idHome to match.idHome,
                Match.idAway to match.idAway,
                Match.homeShot to match.homeShot,
                Match.awayShot to match.awayShot,
                Match.homeYellowCard to match.homeYellowCard,
                Match.awayYellowCard to match.awayYellowCard,
                Match.homeRedCard to match.homeRedCard,
                Match.awayRedCard to match.awayRedCard)
        }

    }

    private fun removeFromFavorite(){
        database.use {
            delete(Match.table_name, "(idEvent = {idEvent})",
                Match.id to matchId)
        }
    }

}
