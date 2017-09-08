package com.br.favoritemusic.data.repository

import com.br.favoritemusic.data.entities.Music
import io.reactivex.Observable
import io.realm.Realm

/**
 * Created by ezequiel.messore on 07/09/17.
 * ezequiel.messore@guaranisistemas.com.br
// */
class MusicDataRepository {

    fun list(): Observable<List<Music>> {
        return Observable.create { emitter ->
            try {
                Realm.getDefaultInstance().use { realm ->
                    val musics = realm.where(Music::class.java).findAll()
                    emitter.onNext(realm.copyFromRealm(musics))
                    emitter.onComplete()
                }
            } catch (error: Throwable) {
                emitter.onError(error)
            }
        }
    }

    fun save(music: Music?): Observable<Music> {
        if (music != null) {
            return Observable.create<Music> { emitter ->
                try {
                    Realm.getDefaultInstance().use { realm ->
                        if (music.id == 0) {
                            val max = realm.where<Music>(Music::class.java).max("id")
                            val nextId: Int
                            if (max == null) {
                                nextId = 1
                            } else {
                                nextId = max.toInt() + 1
                            }
                            music.id = nextId
                        }
                        realm.beginTransaction()
                        realm.copyToRealmOrUpdate(music)
                        realm.commitTransaction()

                        emitter.onNext(music)
                        emitter.onComplete()
                    }
                } catch (error: Throwable) {
                    emitter.onError(error)
                }
            }
        } else {
            return Observable.empty()
        }
    }

    fun delete(music: Music?): Observable<Music> {
        if (music != null) {
            return Observable.create<Music> { emitter ->
                try {
                    Realm.getDefaultInstance().use { realm ->

                        val entity = realm.where<Music>(Music::class.java)
                                .equalTo("id", music.id)
                                .findFirst()

                        realm.beginTransaction()
                        entity.deleteFromRealm()
                        realm.commitTransaction()
                        realm.close()

                        emitter.onNext(music)
                        emitter.onComplete()
                    }
                } catch (error: Throwable) {
                    emitter.onError(error)
                }
            }
        } else {
            return Observable.empty()
        }
    }

}