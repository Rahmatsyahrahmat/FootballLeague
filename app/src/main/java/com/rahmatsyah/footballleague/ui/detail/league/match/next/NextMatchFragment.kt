package com.rahmatsyah.footballleague.ui.detail.league.match.next


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.rahmatsyah.footballleague.R
import com.rahmatsyah.footballleague.model.Match
import com.rahmatsyah.footballleague.ui.detail.league.match.MatchAdapter
import com.rahmatsyah.footballleague.ui.detail.league.match.MatchPresenter
import com.rahmatsyah.footballleague.ui.detail.league.match.MatchView
import kotlinx.android.synthetic.main.fragment_next_match.*

/**
 * A simple [Fragment] subclass.
 */
class NextMatchFragment : Fragment(), MatchView.View {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nextMatchPresenter =
            context?.let { MatchPresenter(it,this) }

        val leagueIds = arguments?.getString("leagueIds")
        if (leagueIds!=null){
            leagueIds.let { nextMatchPresenter?.requestNextMatch(it) }
        }else{
            nextMatchPresenter?.requestNextMatch()
        }
    }

    override fun showMatch(matches: List<Match>) {
        listNextMatch?.layoutManager = LinearLayoutManager(context)
        listNextMatch?.adapter = context?.let { MatchAdapter(it,matches) }
    }

    override fun failureLoadMatch() {
        Toast.makeText(context,"Failure to get match", Toast.LENGTH_LONG).show()
    }

}
