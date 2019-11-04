package com.rahmatsyah.footballleague.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rahmatsyah.footballleague.R
import com.rahmatsyah.footballleague.model.LeagueDetail
import com.rahmatsyah.footballleague.ui.detail.league.LeagueDetailActivity
import kotlinx.android.synthetic.main.view_league.view.*
import org.jetbrains.anko.startActivity

class LeaguesAdapter(private val context:Context,
                     private val leaguesDetail:List<LeagueDetail> = listOf())
    : RecyclerView.Adapter<LeaguesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.view_league, parent, false)
        )
        holder.setIsRecyclable(false)
        return holder
    }

    override fun getItemCount(): Int = leaguesDetail.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val leagueDetail = leaguesDetail[position]
        holder.bindLeague(leagueDetail)
        holder.itemView.setOnClickListener {
            context.startActivity<LeagueDetailActivity>("leagueIds" to leagueDetail.idLeague)
        }
    }

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

        fun bindLeague(leagueDetail: LeagueDetail){
            itemView.tvTeamName.text = leagueDetail.strLeague
            if (leagueDetail.badge!=null)Glide.with(itemView.context).load(leagueDetail.badge).into(itemView.ivTeamLogo)
        }
    }


}