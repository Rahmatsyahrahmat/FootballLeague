package com.rahmatsyah.footballleague.ui.main.favorite


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.rahmatsyah.footballleague.R
import com.rahmatsyah.footballleague.ui.detail.league.MatchPagerAdapter
import kotlinx.android.synthetic.main.fragment_favorite_match.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMatchFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vpFavorite?.adapter = MatchPagerAdapter(childFragmentManager, null)

        tlFavorite?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                vpFavorite.currentItem = p0?.position ?: 0
            }

        })

        vpFavorite?.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tlFavorite))

    }

    override fun onResume() {
        super.onResume()
        vpFavorite?.adapter = MatchPagerAdapter(childFragmentManager, null)
    }

}
