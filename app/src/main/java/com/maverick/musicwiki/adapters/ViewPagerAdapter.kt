package com.maverick.musicwiki.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.maverick.musicwiki.view.AlbumFragment
import com.maverick.musicwiki.view.ArtistFragment
import com.maverick.musicwiki.view.TrackFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, behaviour:Int) :
    FragmentPagerAdapter(fragmentManager,behaviour) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AlbumFragment()
            1 -> ArtistFragment()
            2 -> TrackFragment()
            else -> AlbumFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Album"
            1 -> return "Artist"
            2 -> return "Track"
        }
        return super.getPageTitle(position)
    }
}