package com.br.favoritemusic.data.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Created by ezequiel.messore on 07/09/17.
 * ezequiel.messore@guaranisistemas.com.br
 */
@PaperParcel
open class Music(
        @PrimaryKey open var id: Int = 0,
        open var artworkUrl60: String = "",
        open var trackName: String = "",
        open var artistName: String = ""
) : PaperParcelable, RealmObject() {

    companion object {
        @JvmField
        val CREATOR = PaperParcelMusic.CREATOR
    }
}