package com.wizeline.kotlincontest.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.wizeline.kotlincontest.R
import com.wizeline.kotlincontest.models.Response
import com.squareup.picasso.Picasso

class TopAlbumsAdapter(var response: Response) : RecyclerView.Adapter<TopAlbumsAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return response.albums.album.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = response.albums.album[position]
        holder.textViewAlbum.text = album.name
        holder.textViewArtist.text = album.artist.name
        Picasso.with(holder.itemView.context).load(album.image[2].url).into(holder.imageViewAlbum)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageViewAlbum = itemView.findViewById(R.id.image_view_album) as ImageView
        var textViewAlbum = itemView.findViewById(R.id.text_view_album) as TextView
        var textViewArtist = itemView.findViewById(R.id.text_view_artist) as TextView
    }
}