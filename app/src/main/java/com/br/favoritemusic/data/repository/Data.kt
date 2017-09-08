package com.br.favoritemusic.data.repository

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by ezequiel.messore on 07/09/17.
 * ezequiel.messore@guaranisistemas.com.br
 */
object Data {
    fun init(context: Context) {
        Realm.init(context)

        val realmConfiguration = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()

        Realm.setDefaultConfiguration(realmConfiguration)
    }
}