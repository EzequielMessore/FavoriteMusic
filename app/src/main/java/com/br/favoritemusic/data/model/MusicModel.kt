package com.br.favoritemusic.data.model

import com.br.favoritemusic.data.entities.Music
import io.reactivex.Observable

/**
 * Created by ezequiel.messore on 07/09/17.
 * ezequiel.messore@guaranisistemas.com.br
 */
interface MusicModel {
    fun searchMusic(search: String): Observable<List<Music>>
    fun getFavoriteSongs(): Observable<List<Music>>
    fun saveFavoriteSong(music: Music): Observable<Music>
    fun delete(music: Music): Observable<Music>
}