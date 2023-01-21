package com.maverick.musicwiki.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maverick.musicwiki.R
import com.maverick.musicwiki.adapters.AlbumAdapter
import com.maverick.musicwiki.models.albumModel.Album
import com.maverick.musicwiki.viewModels.DetailViewModel

class AlbumFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var albumList: List<Album>
    private lateinit var adapter: AlbumAdapter
    private lateinit var progressBar: ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_album, container, false)
        viewModel = activity?.let { ViewModelProvider(it)[DetailViewModel::class.java] }!!

        albumList = ArrayList()
        recyclerView = view.findViewById(R.id.album_recycler_view)
//        progressBar = view.findViewById(R.id.album_progress_bar)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter =
            AlbumAdapter(requireActivity(), albumList, object : AlbumAdapter.AlbumClickListener {
                override fun onAlbumClickListener(position: Int) {
                    val intent = Intent(requireContext(), AlbumDetail::class.java)
                    intent.putExtra("albumName", albumList[position].name)
                    intent.putExtra("artistName",albumList[position].artist.name)
                    startActivity(intent)
                }

            })
        recyclerView.adapter = adapter

        val tagName = viewModel.tagName.value.toString()
        viewModel.getTopAlbum(tagName)
        viewModel.topAlbum.observe(requireActivity()) {
            albumList = it.albums.album
            adapter.albumList = albumList
            adapter.notifyDataSetChanged()
        }
        return view
    }
}