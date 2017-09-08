package com.br.favoritemusic.features.base.presenter

/**
 * Created by ezequiel.messore on 07/set/2017.
 * ezequiel.messore@guaranisistemas.com.br
 */
interface BasePresenter<in View> {
    fun attach(view: View)
    fun detach()
}