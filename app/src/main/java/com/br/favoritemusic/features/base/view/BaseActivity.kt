package com.br.favoritemusic.features.base.view.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.br.favoritemusic.features.base.presenter.BasePresenter

/**
 * Created by ezequiel.messore on 25/ago/2017.
 * ezequiel.messore@guaranisistemas.com.br
 */
abstract class BaseActivity : AppCompatActivity() {

    //region Activity Methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @Suppress("UNCHECKED_CAST")
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setUp(savedInstanceState)
        if (this is ViewPresenter<*>) {
            val view = this as ViewPresenter<BasePresenter<Any>>
            view.presenter.attach(this)
        }
        init(savedInstanceState)
    }

    override fun onDestroy() {
        if (this is ViewPresenter<*>) {
            presenter.detach()
        }
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
    //endregion

    //region Utilities methods
    open fun setUp(savedInstanceState: Bundle?) {

    }

    abstract fun init(savedInstanceState: Bundle?)
    //endregion
}


