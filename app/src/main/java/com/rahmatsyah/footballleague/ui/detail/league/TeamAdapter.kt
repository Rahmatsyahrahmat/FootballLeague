package com.rahmatsyah.footballleague.ui.detail.league

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rahmatsyah.footballleague.R
import com.rahmatsyah.footballleague.model.Team
import kotlinx.android.synthetic.main.view_team.view.*

class TeamAdapter(val context:Context, private val teams: List<Team>): RecyclerView.Adapter<TeamAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_team,parent,false))

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = teams[position]
        holder.bind(team)
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        fun bind(team:Team){
            itemView.ivTeamLogo.let { Glide.with(itemView.context).load(team.badge).into(it) }
            itemView.tvTeamName.text = team.name
        }

    }
}