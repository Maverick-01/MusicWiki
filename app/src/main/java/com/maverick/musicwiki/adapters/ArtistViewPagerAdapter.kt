package com.maverick.musicwiki.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.maverick.musicwiki.view.TopAlbumFragment
import com.maverick.musicwiki.view.TopTrackFragment

class ArtistViewPagerAdapter(fragmentManager: FragmentManager, behaviour: Int) :
    FragmentPagerAdapter(fragmentManager, behaviour) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TopTrackFragment()
            1 -> TopAlbumFragment()
            else -> TopTrackFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Top Tracks"
            1 -> return "Top Albums"
        }
        return super.getPageTitle(position)
    }
}