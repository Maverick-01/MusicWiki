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
import com.maverick.musicwiki.models.topArtistAlbum.Album

class TopAlbumAdapter(
    private val context: Context,
    var topAlbumList: List<Album>,
    private val topAlbumClickListener: TopAlbumClickListener
) :
    RecyclerView.Adapter<TopAlbumAdapter.TopAlbumViewHolder>() {

    interface TopAlbumClickListener{
        fun onTopAlbumClickListener(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopAlbumViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_album, parent, false)
        return TopAlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopAlbumViewHolder, position: Int) {
        holder.bind(position)
        holder.albumName.text = topAlbumList[position].name
        holder.artistName.text = topAlbumList[position].artist.name
        Glide.with(context).load(topAlbumList[position].image[0].text).into(holder.albumImage)
    }

    override fun getItemCount() = topAlbumList.size

    inner class TopAlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val albumName: TextView = itemView.findViewById(R.id.tv_album_name)
        val artistName: TextView = itemView.findViewById(R.id.tv_album_artist)
        val albumImage: ImageView = itemView.findViewById(R.id.iv_album)
        val albumCard: CardView = itemView.findViewById(R.id.album_card)

        fun bind(position:Int){
            albumCard.setOnClickListener {
                topAlbumClickListener.onTopAlbumClickListener(position)
            }
        }
    }
}