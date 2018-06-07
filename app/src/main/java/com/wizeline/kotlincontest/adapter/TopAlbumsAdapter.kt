package com.wizeline.kotlincontest.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.wizeline.kotlincontest.R
import com.wizeline.kotlincontest.models.Album
import kotlinx.android.synthetic.main.item_album.view.*

class TopAlbumsAdapter : RecyclerView.Adapter<TopAlbumsAdapter.ViewHolder>() {

    class Sizes {
        companion object {
            const val MEDIUM = "medium"
        }
    }

    private var albums: List<Album> = ArrayList()

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    fun setResponse(albums: List<Album>) {
        this.albums = albums
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(album: Album) {
            val context = itemView.context
            itemView.textViewAlbum.text = album.name
            itemView.textViewArtist.text = album.artist.name
            for (url in album.image) {
                if (url.size.contentEquals(Sizes.MEDIUM)) {
                    Picasso.with(context).load(url.url).into(itemView.imageViewAlbum)
                    break
                }
            }
        }
    }
}