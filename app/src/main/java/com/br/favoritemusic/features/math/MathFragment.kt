package com.br.favoritemusic.features.math

import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.br.favoritemusic.R
import com.br.favoritemusic.data.entities.Music
import com.br.favoritemusic.data.model.impl.MusicModelImpl
import com.br.favoritemusic.features.base.MusicListAdapter
import com.br.favoritemusic.features.base.view.BaseFragment
import com.br.favoritemusic.features.base.view.view.ViewPresenter
import kotlinx.android.synthetic.main.fragment_math.*

/**
 * Created by ezequiel.messore on 07/09/17.
 * ezequiel.messore@guaranisistemas.com.br
 */
class MathFragment : BaseFragment(),
        MathContract.View, ViewPresenter<MathContract.Presenter> {

    private lateinit var adapter: MusicListAdapter
    override lateinit var presenter: MathContract.Presenter
    private var list: ArrayList<Music>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_math, container, false)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            list = savedInstanceState.getParcelableArrayList(KEY_MUSIC)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (list != null) {
            outState.putParcelableArrayList(KEY_MUSIC, list)
        }
    }

    override fun setUp(view: View?, savedInstanceState: Bundle?) {
        presenter = MathPresenter(MusicModelImpl())
        createAdapter()
    }

    private fun createAdapter() {
        val listener = object : MusicListAdapter.OnMusicListener {
            override fun onClickLike(music: Music) {
                presenter.save(music)
            }

            override fun onClickUnLike(music: Music) {
                list!!.remove(music)
                adapter.items = list
            }

            override fun onShowLike(): Boolean   {
                return true
            }
        }

        adapter = MusicListAdapter(listener)
    }

    override fun init(view: View?, savedInstanceState: Bundle?) {
        if (list != null) {
            adapter.items = list
        }
        bind(view)
    }

    fun bind(view: View?) {
        if (view == null) return

        recyclerViewMath.adapter = adapter

        searchView.setIconifiedByDefault(true)
        searchView.isIconified = false
        searchView.isFocusable = false
        searchView.clearFocus()


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                presenter.search(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        searchView.setOnCloseListener {
            true
        }
    }

    //region MathContract.View

    override fun listMusics(list: List<Music>) {
        this.list = ArrayList(list)
        this.adapter.items = list
        searchView.clearFocus()

    }

    override fun onSuccess(music: Music) {
        showToast(getString(R.string.msg_music_save, music.trackName))
    }

    //endregion

    //region companion
    companion object {
        val TAG = MathFragment::class.java.simpleName!!
        val KEY_MUSIC = "music"

        fun newInstance(): MathFragment {
            val fragment = MathFragment()
            return fragment
        }
    }
    //endregion

}