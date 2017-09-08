package com.br.favoritemusic.features.favorite

import com.br.favoritemusic.data.entities.Music
import com.br.favoritemusic.features.base.presenter.BasePresenter
import com.br.favoritemusic.features.base.view.view.LoadingView
import io.reactivex.Observable

/**
 * Created by ezequiel.messore on 07/09/17.
 * ezequiel.messore@guaranisistemas.com.br
 */
interface FavoriteSongsContract {
    interface View: LoadingView {
        fun listMusics(list: List<Music>)
        fun delete(music: Music)
    }

    interface Presenter: BasePresenter<View>{
        fun getMusics(): Observable<List<Music>>
        fun delete(music: Music): Observable<Music>
    }
}