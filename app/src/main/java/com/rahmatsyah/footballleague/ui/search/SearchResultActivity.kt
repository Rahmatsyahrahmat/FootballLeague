package com.rahmatsyah.footballleague.ui.search

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahmatsyah.footballleague.R
import com.rahmatsyah.footballleague.model.Match
import com.rahmatsyah.footballleague.ui.detail.league.match.MatchAdapter
import kotlinx.android.synthetic.main.activity_search_result.*

class SearchResultActivity : AppCompatActivity(), SearchResultView.View {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let { handleIntent(it) }
    }

    private fun handleIntent(intent: Intent) {

        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            val searchResultPresenter = SearchResultPresenter(this)
            searchResultPresenter.requestMatch(query)
        }
    }

    override fun showMatch(matches: List<Match>) {
        listMatch.layoutManager = LinearLayoutManager(this)
        listMatch.adapter = MatchAdapter(this,matches)
    }

    override fun failureLoadMatch() {
        Toast.makeText(this,"Failure to get match",Toast.LENGTH_LONG).show()
    }

    override fun noMatch() {
        tvNoMatch.visibility = View.VISIBLE
    }
}
