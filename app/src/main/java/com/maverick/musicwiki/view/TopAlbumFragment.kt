package com.maverick.musicwiki.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maverick.musicwiki.R
import com.maverick.musicwiki.adapters.TopAlbumAdapter
import com.maverick.musicwiki.models.topArtistAlbum.Album
import com.maverick.musicwiki.viewModels.ArtistDetailViewModel

class TopAlbumFragment : Fragment() {
    private lateinit var viewModel: ArtistDetailViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var artistTopAlbum: List<Album>
    private lateinit var adapter: TopAlbumAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_top_album, container, false)
        viewModel = activity?.let { ViewModelProvider(it)[ArtistDetailViewModel::class.java] }!!

        artistTopAlbum = ArrayList()
        recyclerView = view.findViewById(R.id.top_album_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = TopAlbumAdapter(
            requireActivity(),
            artistTopAlbum,
            object : TopAlbumAdapter.TopAlbumClickListener {
                override fun onTopAlbumClickListener(position: Int) {
                    val intent = Intent(requireContext(), AlbumDetail::class.java)
                    intent.putExtra("albumName", artistTopAlbum[position].name)
                    intent.putExtra("artistName", artistTopAlbum[position].artist.name)
                    startActivity(intent)
                }

            })
        recyclerView.adapter = adapter

        val artistName = viewModel.artistName.value.toString()
        Log.d("art", artistName)
        viewModel.getArtistTopAlbum(artistName)
        viewModel.artistTopAlbum.observe(requireActivity()) {
            artistTopAlbum = it.topalbums.album
            Log.d("art1", it.toString())
            adapter.topAlbumList = artistTopAlbum
            adapter.notifyDataSetChanged()
        }
        return view
    }
}