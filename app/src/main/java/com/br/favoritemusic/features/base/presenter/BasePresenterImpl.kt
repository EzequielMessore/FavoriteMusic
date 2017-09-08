package com.br.favoritemusic.features.base.presenter

import com.br.favoritemusic.features.base.view.view.BaseView
import com.br.favoritemusic.features.base.view.view.LoadingView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference


/**
 * Created by ezequiel.messore on 07/set/2017.
 * ezequiel.messore@guaranisistemas.com.br
 */
open class BasePresenterImpl<View> : BasePresenter<View> {

    private var viewReference: WeakReference<View>? = null
    private var disposables: CompositeDisposable? = null

    protected var view: View? = null
        get() = viewReference?.get()
        private set

    //region BasePresenter
    override fun attach(view: View) {
        viewReference = WeakReference<View>(view)
    }

    override fun detach() {
        viewReference?.clear()
        if (disposables != null && !disposables!!.isDisposed) {
            disposables!!.dispose()
        }
    }
    //endregion

    //region functions

    fun <T> execWithView(observable: Observable<T>, disposable: DisposableObserver<T>): Observable<T> {
        toggleLoading(true)
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(
                        { toggleLoading(false) }
                )
                .doOnError({ error ->
                    toggleLoading(false)
                    if (view is BaseView) {
                        (view as BaseView).showError(error)
                    }
                })
                .subscribeWith(disposable)
        addDisposable(disposable)
        return observable
    }

    private fun toggleLoading(visible: Boolean) {
        if (view is LoadingView) {
            if (visible) {
                (view as LoadingView).showLoading()
            } else {
                (view as LoadingView).hideLoading()
            }
        }
    }

    private fun addDisposable(disposable: Disposable) {
        if (disposables == null) {
            disposables = CompositeDisposable()
        }
        disposables!!.add(disposable)
    }
    //endregion
}