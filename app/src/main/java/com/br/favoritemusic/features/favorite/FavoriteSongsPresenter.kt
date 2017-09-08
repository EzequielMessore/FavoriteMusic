package com.br.favoritemusic.features.favorite

import com.br.favoritemusic.data.entities.Music
import com.br.favoritemusic.data.model.MusicModel
import com.br.favoritemusic.features.base.presenter.BasePresenterImpl
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver

/**
 * Created by ezequiel.messore on 07/09/17.
 * ezequiel.messore@guaranisistemas.com.br
 */
class FavoriteSongsPresenter(private val musicModel: MusicModel) :
        FavoriteSongsContract.Presenter, BasePresenterImpl<FavoriteSongsContract.View>() {

    override fun getMusics(): Observable<List<Music>> =
            execWithView(musicModel.getFavoriteSongs(), GetListObservable())

    override fun delete(music: Music): Observable<Music>
            = execWithView(musicModel.delete(music), DeleteObservable())


    inner class GetListObservable : DisposableObserver<List<Music>>() {
        override fun onNext(musics: List<Music>) {
            view?.listMusics(musics)
        }

        override fun onComplete() {

        }

        override fun onError(e: Throwable) {

        }
    }

    inner class DeleteObservable : DisposableObserver<Music>() {
        override fun onNext(music: Music) {
            view?.delete(music)
        }

        override fun onComplete() {

        }

        override fun onError(e: Throwable) {

        }
    }
}