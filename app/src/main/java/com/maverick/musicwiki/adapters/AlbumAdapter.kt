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
import com.maverick.musicwiki.models.albumModel.Album
import com.maverick.musicwiki.models.albumModel.AlbumModel

class AlbumAdapter(
    private val context: Context,
    var albumList: List<Album>,
    private val albumClickListener: AlbumClickListener
) :
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    interface AlbumClickListener {
        fun onAlbumClickListener(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(position)
        holder.albumName.text = albumList[position].name
        holder.artistName.text = albumList[position].artist.name
        Glide.with(context).load(albumList[position].image[0].text).into(holder.albumImage)
    }

    override fun getItemCount() = albumList.size

    inner class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val albumName: TextView = itemView.findViewById(R.id.tv_album_name)
        val artistName: TextView = itemView.findViewById(R.id.tv_album_artist)
        val albumImage: ImageView = itemView.findViewById(R.id.iv_album)
        val albumCard: CardView = itemView.findViewById(R.id.album_card)

        fun bind(position: Int) {
            albumCard.setOnClickListener {
                albumClickListener.onAlbumClickListener(position)
            }
        }
    }

}