package com.maverick.musicwiki.view

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.maverick.musicwiki.R
import com.maverick.musicwiki.adapters.ViewPagerAdapter
import com.maverick.musicwiki.viewModels.DetailViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var tagName: TextView
    private lateinit var tagDescription: TextView
    private var albumName: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        viewModel.setTagName(intent.getStringExtra("tagName").toString())
        Log.d("tagname", viewModel.tagName.value.toString())

        tagName = findViewById(R.id.tv_title)
        tagDescription = findViewById(R.id.tv_body)

        albumName = intent.getStringExtra("tagName").toString().uppercase()
        tagName.text = albumName
        viewModel.getData(albumName)
        viewModel.result.observe(this) {
            tagDescription.text = it.tag.wiki.summary
        }

        //view pager and tab layout
        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = ViewPagerAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        tabLayout.setupWithViewPager(viewPager)
    }
}