package com.br.favoritemusic.data.model.impl

import com.br.favoritemusic.data.entities.Music
import com.br.favoritemusic.data.model.MusicModel
import com.br.favoritemusic.data.net.ServiceGenerator
import com.br.favoritemusic.data.net.music.MusicService
import com.br.favoritemusic.data.repository.MusicDataRepository
import io.reactivex.Observable

/**
 * Created by ezequiel.messore on 07/09/17.
 * ezequiel.messore@guaranisistemas.com.br
 */
class MusicModelImpl : MusicModel {
    override fun searchMusic(search: String): Observable<List<Music>>
            = ServiceGenerator.getService(MusicService::class.java)
            .searchMusic("", "", search)
            .map { (results) -> results }

    override fun getFavoriteSongs(): Observable<List<Music>>
            = MusicDataRepository().list()

    override fun saveFavoriteSong(music: Music): Observable<Music>
            = MusicDataRepository().save(music)

    override fun delete(music: Music): Observable<Music>
            = MusicDataRepository().delete(music)

}