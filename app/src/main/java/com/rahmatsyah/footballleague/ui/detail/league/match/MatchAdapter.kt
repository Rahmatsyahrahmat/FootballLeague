package com.rahmatsyah.footballleague.ui.detail.league.match

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rahmatsyah.footballleague.R
import com.rahmatsyah.footballleague.model.Match
import com.rahmatsyah.footballleague.model.Team
import com.rahmatsyah.footballleague.ui.detail.match.MatchDetailActivity
import kotlinx.android.synthetic.main.view_match.view.*
import org.jetbrains.anko.startActivity

class MatchAdapter(private val context: Context, private val matches:List<Match>): RecyclerView.Adapter<MatchAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_match,parent,false))
        holder.setIsRecyclable(false)
        return holder
    }


    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val match = matches[position]
        holder.bind(match)
        holder.itemView.setOnClickListener {
            context.startActivity<MatchDetailActivity>("matchIds" to match.id)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), MatchTeamView.View {

        private val presenter = MatchTeamPresenter(this)

        @SuppressLint("SetTextI18n")
        fun bind(match:Match){
            itemView.tvTitle.text = match.title
            itemView.tvDate.text = match.date
            if (match.homeScore!=null&&match.awayScore!=null){
                itemView.tvFt.text = "FT"
                itemView.tvScore.text = match.homeScore+" : "+match.awayScore
            }
            itemView.tvHome.text = match.title.split(" vs ")[0]
            itemView.tvAway.text = match.title.split(" vs ")[1]
            presenter.requestHomeTeam(match.idHome)
            presenter.requestAwayTeam(match.idAway)
        }

        override fun showHomeTeam(team: Team) {
            itemView.ivHome.let { Glide.with(itemView.context.applicationContext).load(team.badge).into(it) }
        }

        override fun showAwayTeam(team: Team) {
            itemView.ivAway.let { Glide.with(itemView.context.applicationContext).load(team.badge).into(it) }
        }

    }

}