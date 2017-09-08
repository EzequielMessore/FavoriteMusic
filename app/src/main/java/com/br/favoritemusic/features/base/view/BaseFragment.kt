package com.br.favoritemusic.features.base.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.br.favoritemusic.features.base.presenter.BasePresenter
import com.br.favoritemusic.features.base.view.view.LoadingView
import com.br.favoritemusic.features.base.view.view.ViewPresenter
import com.br.favoritemusic.features.base.view.view.dialog.ProgressDialog

/**
 * Created by ezequiel.messore on 07/09/17.
 * ezequiel.messore@guaranisistemas.com.br
 */
abstract class BaseFragment : Fragment(), LoadingView {

    private lateinit var dialogFragment: ProgressDialog

    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialogFragment = ProgressDialog()
        setUp(view, savedInstanceState)
        if (this is ViewPresenter<*>) {
            (this as ViewPresenter<BasePresenter<Any>>).presenter.attach(this)
        }
        init(view, savedInstanceState)
    }

    override fun onDestroy() {
        if (this is ViewPresenter<*>) {
            presenter.detach()
        }
        super.onDestroy()
    }

    open fun setUp(view: View?, savedInstanceState: Bundle?) {

    }

    abstract fun init(view: View?, savedInstanceState: Bundle?)

    override fun showLoading() {
        dialogFragment.show(childFragmentManager, BaseFragment::class.java.simpleName)
    }

    override fun hideLoading() {
        dialogFragment.dismiss()
    }

    override fun showError(error: Throwable) {
        showToast(error.message)
    }

    fun showToast(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}