package com.maverick.musicwiki.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maverick.musicwiki.R
import com.maverick.musicwiki.adapters.AlbumDetailTagAdapter
import com.maverick.musicwiki.models.albumDetail.Tag
import com.maverick.musicwiki.viewModels.AlbumDetailViewModel

class AlbumDetail : AppCompatActivity() {
    private lateinit var viewModel: AlbumDetailViewModel
    private lateinit var albumImage: ImageView
    private lateinit var albumName: TextView
    private lateinit var albumBody: TextView
    private lateinit var albumArtist: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AlbumDetailTagAdapter
    private lateinit var tagList: List<Tag>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)

        albumImage = findViewById(R.id.album_detail_image)
        albumName = findViewById(R.id.album_detail_name)
        albumArtist = findViewById(R.id.album_artist_name)
        albumBody = findViewById(R.id.album_detail_body)

        recyclerView = findViewById(R.id.tag_recycler_view)
        tagList = ArrayList()
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        adapter = AlbumDetailTagAdapter(this, tagList, object : AlbumDetailTagAdapter.TagClickListener {
            override fun onTagClickListener(position: Int) {
                val intent = Intent(this@AlbumDetail, DetailActivity::class.java)
                intent.putExtra("tagName",tagList[position].name)
                startActivity(intent)
            }

        })
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this)[AlbumDetailViewModel::class.java]
        val album = intent.getStringExtra("albumName").toString()
        val artist = intent.getStringExtra("artistName").toString()
        viewModel.getAlbumDetail(artist, album)
        viewModel.albumDetail.observe(this) {

            tagList = it.album.tags.tag
            adapter.tagList = tagList
            adapter.notifyDataSetChanged()


            //here you can get empty wiki null pointer execption

            Glide.with(this).load(it.album.image[2].text).into(albumImage)
            albumName.text = it.album.name
            albumArtist.text = it.album.artist
//            if (it.album.wiki.summary != null)
            albumBody.text = it.album.wiki.summary
        }
    }
}