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
import com.maverick.musicwiki.models.trackModel.Track

class TrackAdapter(private val context: Context, var trackList: List<Track>) :
    RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_album, parent, false)
        return TrackViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(position)
        holder.trackName.text = trackList[position].name
        holder.artistName.text = trackList[position].artist.name
        Glide.with(context).load(trackList[position].image[0].text).into(holder.trackImage)
    }

    override fun getItemCount() = trackList.size

    inner class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val trackName: TextView = itemView.findViewById(R.id.tv_album_name)
        val artistName: TextView = itemView.findViewById(R.id.tv_album_artist)
        val trackImage: ImageView = itemView.findViewById(R.id.iv_album)

        fun bind(position: Int) {

        }
    }

}