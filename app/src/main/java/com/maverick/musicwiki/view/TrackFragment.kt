package com.maverick.musicwiki.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maverick.musicwiki.R
import com.maverick.musicwiki.adapters.TrackAdapter
import com.maverick.musicwiki.models.trackModel.Track
import com.maverick.musicwiki.viewModels.DetailViewModel

class TrackFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var trackList: List<Track>
    private lateinit var adapter: TrackAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_track, container, false)
        viewModel = activity?.let { ViewModelProvider(it)[DetailViewModel::class.java] }!!

        trackList = ArrayList()
        recyclerView = view.findViewById(R.id.track_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = TrackAdapter(requireActivity(), trackList)
        recyclerView.adapter = adapter

        val tagName = viewModel.tagName.value.toString()
        viewModel.getTopTrack(tagName)
        viewModel.topTrack.observe(requireActivity()) {
            trackList = it.tracks.track
            adapter.trackList = trackList
            adapter.notifyDataSetChanged()
        }
        return view
    }
}