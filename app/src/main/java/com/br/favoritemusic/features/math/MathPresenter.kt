package com.br.favoritemusic.features.math

import com.br.favoritemusic.data.entities.Music
import com.br.favoritemusic.data.model.MusicModel
import com.br.favoritemusic.features.base.presenter.BasePresenterImpl
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver

/**
 * Created by ezequiel.messore on 07/09/17.
 * ezequiel.messore@guaranisistemas.com.br
 */
class MathPresenter(private val musicModel: MusicModel) :
        MathContract.Presenter, BasePresenterImpl<MathContract.View>() {

    override fun search(search: String): Observable<List<Music>>
            = execWithView(musicModel.searchMusic(search), MusicObservable())

    override fun save(music: Music) {
        execWithView(musicModel.saveFavoriteSong(music), SaveMusicObservable())
    }

    inner class SaveMusicObservable : DisposableObserver<Music>() {
        override fun onNext(musics: Music) {
            view?.onSuccess(musics)
        }

        override fun onComplete() {

        }

        override fun onError(e: Throwable) {
            view?.showError(e)
        }
    }

    inner class MusicObservable : DisposableObserver<List<Music>>() {
        override fun onNext(musics: List<Music>) {
            view?.listMusics(ArrayList(musics))
        }

        override fun onComplete() {

        }

        override fun onError(e: Throwable) {
            view?.showError(e)
        }
    }
}