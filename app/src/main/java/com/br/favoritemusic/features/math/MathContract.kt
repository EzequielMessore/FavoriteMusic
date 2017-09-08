package com.br.favoritemusic.features.math

import com.br.favoritemusic.data.entities.Music
import com.br.favoritemusic.features.base.presenter.BasePresenter
import com.br.favoritemusic.features.base.view.view.LoadingView
import io.reactivex.Observable

/**
 * Created by ezequiel.messore on 25/ago/2017.
 * ezequiel.messore@guaranisistemas.com.br
 */
interface MathContract {
    interface View : LoadingView {
        fun listMusics(list: List<Music>)
        fun onSuccess(music: Music)
    }

    interface Presenter : BasePresenter<View> {
        fun search(search: String): Observable<List<Music>>
        fun save(music: Music)
    }
}