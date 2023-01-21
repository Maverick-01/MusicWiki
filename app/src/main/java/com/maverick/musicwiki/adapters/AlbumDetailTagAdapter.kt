package com.maverick.musicwiki.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.maverick.musicwiki.R
import com.maverick.musicwiki.models.albumDetail.Tag

class AlbumDetailTagAdapter (
    private val context: Context,
    var tagList: List<Tag>,
    private val tagClickListener: TagClickListener
) : RecyclerView.Adapter<AlbumDetailTagAdapter.AlbumDetailTagViewHolder>() {

    interface TagClickListener {
        fun onTagClickListener(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetailTagViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_tag, parent, false)
        return AlbumDetailTagViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumDetailTagViewHolder, position: Int) {
        holder.textView.text = tagList[position].name
        holder.bind(position)
    }

    override fun getItemCount() = tagList.size

    inner class AlbumDetailTagViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.tv_name)
        private val textCardView: CardView = itemView.findViewById(R.id.tv_card)
        fun bind(position: Int) {
            textCardView.setOnClickListener {
                tagClickListener.onTagClickListener(position)
            }
        }
    }
}