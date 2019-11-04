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
            MatchPresenter(this)
        arguments?.getString("leagueIds")?.let { nextMatchPresenter.requestNextMatch(it) }
    }

    override fun showMatch(matchs: List<Match>) {
        listNextMatch.layoutManager = LinearLayoutManager(context)
        listNextMatch.adapter = context?.let { MatchAdapter(it,matchs) }
    }

    override fun failureLoadMatch() {
        Toast.makeText(context,"Failure to get match", Toast.LENGTH_LONG).show()
    }

}
