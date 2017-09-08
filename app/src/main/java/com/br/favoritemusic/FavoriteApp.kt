package com.br.favoritemusic

import android.app.Application
import com.br.favoritemusic.data.repository.Data
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * Created by ezequiel.messore on 07/09/17.
 * ezequiel.messore@guaranisistemas.com.br
 */
class FavoriteApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        Data.init(this)
    }

}