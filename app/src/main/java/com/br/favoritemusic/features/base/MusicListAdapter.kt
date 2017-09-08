package com.br.favoritemusic.features.base

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.tokenlab.favoritegames.extension.hide
import br.com.tokenlab.favoritegames.extension.show
import com.br.favoritemusic.R
import com.br.favoritemusic.data.entities.Music
import kotlinx.android.synthetic.main.item_music.view.*

/**
* Created by ezequiel.messore on 07/09/17.
* ezequiel.messore@guaranisistemas.com.br
*/;
class MusicListAdapter(val listener: OnMusicListener) :
        RecyclerView.Adapter<MusicListAdapter.MusicViewHolder>() {

    private val itemsList = ArrayList<Music>()

    var items: List<Music>?
        get() = itemsList
        set(value) {
            itemsList.clear()
            if (value != null && value.isNotEmpty()) {
                itemsList.addAll(value)
            }
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        return MusicViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_music, parent, false))
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount() = items!!.size

    private fun getItem(position: Int) = itemsList[position]

    inner class MusicViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(music: Music) {
            itemView.iv_track.setImageURI(null as String?)
            itemView.iv_track.setImageURI(music.artworkUrl60)

            itemView.tv_track_name.text = music.trackName
            itemView.tv_artist_name.text = music.artistName

            if (listener.onShowLike()) {
                itemView.iv_like.show()
            } else {
                itemView.iv_like.hide()
            }

            itemView.iv_like.setOnClickListener { listener.onClickLike(music) }
            itemView.iv_unlike.setOnClickListener { listener.onClickUnLike(music) }

        }
    }

    interface OnMusicListener {
        fun onClickLike(music: Music)
        fun onClickUnLike(music: Music)
        fun onShowLike(): Boolean
    }

}