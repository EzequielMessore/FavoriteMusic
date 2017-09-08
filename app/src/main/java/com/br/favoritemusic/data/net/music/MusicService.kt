package com.br.favoritemusic.data.net.music

import com.br.favoritemusic.data.entities.Result
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ezequiel.messore on 07/09/17.
 * ezequiel.messore@guaranisistemas.com.br
 */
interface MusicService {
    @GET("/search")
    fun searchMusic(
            @Query("resultEntity") entity: String = "music",
            @Query("country") country: String = "BR",
            @Query("term") search: String): Observable<Result>
}