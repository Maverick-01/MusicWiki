package com.maverick.musicwiki.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maverick.musicwiki.R
import com.maverick.musicwiki.models.topArtistTracks.Track

class TopTrackAdapter(
    private val context: Context,
    var topTrackList: List<Track>,
) :
    RecyclerView.Adapter<TopTrackAdapter.TopTrackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopTrackViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_album, parent, false)
        return TopTrackViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopTrackViewHolder, position: Int) {
        holder.albumName.text = topTrackList[position].name
        holder.artistName.text = topTrackList[position].artist.name
        Glide.with(context).load(topTrackList[position].image[0].text).into(holder.albumImage)
    }

    override fun getItemCount() = topTrackList.size

    inner class TopTrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val albumName: TextView = itemView.findViewById(R.id.tv_album_name)
        val artistName: TextView = itemView.findViewById(R.id.tv_album_artist)
        val albumImage: ImageView = itemView.findViewById(R.id.iv_album)
    }
}