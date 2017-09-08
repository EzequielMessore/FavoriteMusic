package com.br.favoritemusic.features

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.br.favoritemusic.R
import com.br.favoritemusic.features.favorite.FavoriteSongsFragment
import com.br.favoritemusic.features.math.MathFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            when (item.itemId) {
                R.id.navigation_math -> {
                    fragmentTransaction.replace(R.id.container,
                            MathFragment.newInstance(),
                            MathFragment.TAG)
                            .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_like -> {
                    fragmentTransaction.replace(R.id.container,
                            FavoriteSongsFragment.newInstance(),
                            FavoriteSongsFragment.TAG)
                            .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })

        navigation.selectedItemId = R.id.navigation_math
    }
}
