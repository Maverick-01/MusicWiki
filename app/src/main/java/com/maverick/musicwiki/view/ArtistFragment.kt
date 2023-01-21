package com.maverick.musicwiki.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maverick.musicwiki.R
import com.maverick.musicwiki.adapters.ArtistAdapter
import com.maverick.musicwiki.models.artistModel.Artist
import com.maverick.musicwiki.viewModels.DetailViewModel


class ArtistFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var artistList: List<Artist>
    private lateinit var adapter: ArtistAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_artist, container, false)
        viewModel = activity?.let { ViewModelProvider(it)[DetailViewModel::class.java] }!!

        artistList = ArrayList()
        recyclerView = view.findViewById(R.id.artist_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = ArtistAdapter(requireActivity(), artistList,object : ArtistAdapter.ArtistClickListener{
            override fun onArtistClickListener(position: Int) {
                val intent = Intent(requireContext(), ArtistDetail::class.java)
                intent.putExtra("artistName",artistList[position].name)
                startActivity(intent)
            }

        })
        recyclerView.adapter = adapter

        val tagName = viewModel.tagName.value.toString()
        viewModel.getTopArtist(tagName)
        viewModel.topArtist.observe(requireActivity()) {
            artistList = it.topartists.artist
            adapter.artistList = artistList
            adapter.notifyDataSetChanged()
        }
        return view
    }
}