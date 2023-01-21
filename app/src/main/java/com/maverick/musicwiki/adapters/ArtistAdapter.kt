package com.maverick.musicwiki.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maverick.musicwiki.R
import com.maverick.musicwiki.models.artistModel.Artist

class ArtistAdapter(
    private val context: Context,
    var artistList: List<Artist>,
    private val artistClickListener: ArtistClickListener
) :
    RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {

    interface ArtistClickListener {
        fun onArtistClickListener(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_album, parent, false)
        return ArtistViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bind(position)
        holder.albumName.text = artistList[position].name
        Glide.with(context).load(artistList[position].image[0].text).into(holder.albumImage)
    }

    override fun getItemCount() = artistList.size

    inner class ArtistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val albumName: TextView = itemView.findViewById(R.id.tv_album_name)
        val albumImage: ImageView = itemView.findViewById(R.id.iv_album)
        val albumCard: CardView = itemView.findViewById(R.id.album_card)

        fun bind(position: Int) {
            albumCard.setOnClickListener {
                artistClickListener.onArtistClickListener(position)
            }
        }
    }
}