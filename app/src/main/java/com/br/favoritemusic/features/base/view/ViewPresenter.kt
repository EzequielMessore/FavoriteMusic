package com.br.favoritemusic.features.base.view.view

import com.br.favoritemusic.features.base.presenter.BasePresenter


/**
 * Created by ezequiel.messore on 25/ago/2017.
 * ezequiel.messore@guaranisistemas.com.br
 */
interface ViewPresenter<Presenter : BasePresenter<*>> {
    var presenter: Presenter
}