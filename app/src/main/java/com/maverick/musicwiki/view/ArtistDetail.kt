package com.maverick.musicwiki.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.maverick.musicwiki.R
import com.maverick.musicwiki.adapters.ArtistDetailTagAdapter
import com.maverick.musicwiki.adapters.ArtistViewPagerAdapter
import com.maverick.musicwiki.models.artistInfo.Tag
import com.maverick.musicwiki.viewModels.ArtistDetailViewModel

class ArtistDetail : AppCompatActivity() {
    private lateinit var viewModel: ArtistDetailViewModel
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var artistName: TextView
    private lateinit var artistDescription: TextView
    private lateinit var playCount: TextView
    private lateinit var followers: TextView
    private lateinit var artistImage: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArtistDetailTagAdapter
    private lateinit var tagList: List<Tag>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_detail)

        //view model
        viewModel = ViewModelProvider(this)[ArtistDetailViewModel::class.java]

        artistName = findViewById(R.id.artist_detail_name)
        artistDescription = findViewById(R.id.artist_detail_body)
        playCount = findViewById(R.id.play_count)
        followers = findViewById(R.id.followers)
        artistImage = findViewById(R.id.artist_image)
        recyclerView = findViewById(R.id.artist_recycler_view)
        tagList = ArrayList()
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter =
            ArtistDetailTagAdapter(this, tagList, object : ArtistDetailTagAdapter.TagClickListener {
                override fun onTagClickListener(position: Int) {
                    val intent = Intent(this@ArtistDetail, DetailActivity::class.java)
                    intent.putExtra("tagName", tagList[position].name)
                    startActivity(intent)
                }

            })
        recyclerView.adapter = adapter

        val artist = intent.getStringExtra("artistName").toString()
        artistName.text = artist

        viewModel.setArtistName(artist)
        viewModel.getArtistDetail(artist)

        viewModel.artistDetail.observe(this) {
            tagList = it.artist.tags.tag
            adapter.tagList = tagList
            adapter.notifyDataSetChanged()

            artistDescription.text = it.artist.bio.summary.substring(0,200)
            playCount.text = viewModel.countViews(it.artist.stats.playcount.toLong())
            followers.text = viewModel.countViews(it.artist.stats.listeners.toLong())
            Glide.with(this).load(it.artist.image[2].text).into(artistImage)
        }

        //view pager and tab layout
        tabLayout = findViewById(R.id.artist_tab_layout)
        viewPager = findViewById(R.id.artist_view_pager)
        viewPager.adapter = ArtistViewPagerAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        tabLayout.setupWithViewPager(viewPager)
    }
}