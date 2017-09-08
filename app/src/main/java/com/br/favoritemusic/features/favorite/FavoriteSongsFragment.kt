package com.br.favoritemusic.features.favorite

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.br.favoritemusic.R
import com.br.favoritemusic.data.entities.Music
import com.br.favoritemusic.data.model.impl.MusicModelImpl
import com.br.favoritemusic.features.base.MusicListAdapter
import com.br.favoritemusic.features.base.view.BaseFragment
import com.br.favoritemusic.features.base.view.view.ViewPresenter
import com.br.favoritemusic.features.math.MathFragment

/**
 * Created by ezequiel.messore on 07/09/17.
 * ezequiel.messore@guaranisistemas.com.br
 */
class FavoriteSongsFragment : BaseFragment(),
        FavoriteSongsContract.View, ViewPresenter<FavoriteSongsContract.Presenter> {

    private lateinit var adapter: MusicListAdapter
    override lateinit var presenter: FavoriteSongsContract.Presenter
    private var list: ArrayList<Music>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_songs, container, false)
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
        presenter = FavoriteSongsPresenter(MusicModelImpl())
        createAdapter()
    }

    override fun init(view: View?, savedInstanceState: Bundle?) {
        adapter.items = list
        bind(view)
    }

    private fun bind(view: View?) {
        if (view == null) return
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        presenter.getMusics()
    }

    private fun createAdapter() {
        val listener = object : MusicListAdapter.OnMusicListener {
            override fun onClickLike(music: Music) {

            }

            override fun onClickUnLike(music: Music) {
                presenter.delete(music)
            }

            override fun onShowLike(): Boolean {
                return false
            }

        }

        adapter = MusicListAdapter(listener)
    }

    //region MathContract.View

    override fun listMusics(list: List<Music>) {
        this.list = ArrayList(list)
        adapter.items = list
    }

    override fun delete(music: Music) {
        this.list!!.remove(music)
        adapter.items = list
    }

    //endregion

    //region companion
    companion object {
        val TAG = MathFragment::class.java.simpleName!!
        val KEY_MUSIC = "music"

        fun newInstance(): FavoriteSongsFragment {
            val fragment = FavoriteSongsFragment()
            return fragment
        }
    }
    //endregion

}