package com.maverick.musicwiki.view

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
import com.maverick.musicwiki.adapters.TopTrackAdapter
import com.maverick.musicwiki.models.topArtistTracks.Track
import com.maverick.musicwiki.viewModels.ArtistDetailViewModel

class TopTrackFragment : Fragment() {
    private lateinit var viewModel: ArtistDetailViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var artistTopTrack: List<Track>
    private lateinit var adapter: TopTrackAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_top_track, container, false)
        viewModel = activity?.let { ViewModelProvider(it)[ArtistDetailViewModel::class.java] }!!

        artistTopTrack = ArrayList()
        recyclerView = view.findViewById(R.id.top_track_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = TopTrackAdapter(requireActivity(), artistTopTrack)
        recyclerView.adapter = adapter

        val artistName = viewModel.artistName.value.toString()
        Log.d("art", artistName)
        viewModel.getArtistTopTrack(artistName)
        viewModel.artistTopTrack.observe(requireActivity()) {
            artistTopTrack = it.toptracks.track
            Log.d("art1", it.toString())
            adapter.topTrackList = artistTopTrack
            adapter.notifyDataSetChanged()
        }
        return view
    }
}