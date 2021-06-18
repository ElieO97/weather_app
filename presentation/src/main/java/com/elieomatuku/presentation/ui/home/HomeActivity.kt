package com.elieomatuku.presentation.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.elieomatuku.presentation.R
import com.elieomatuku.presentation.ui.base.BaseActivity
import com.elieomatuku.presentation.ui.favourites.FavouritesActivity
import com.elieomatuku.presentation.ui.weather.CurrentLocationWeatherFragment
import kotlinx.android.synthetic.main.activity_home.*

/**
 * Created by elieomatuku on 2021-06-13
 */

class HomeActivity : BaseActivity(R.layout.activity_home) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_dehaze_24)

        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.favourites_item -> {
                    startActivity(Intent(this, FavouritesActivity::class.java))
                }

                R.id.map_item -> {
                }
            }
            false
        }

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.navHostContainer,
                CurrentLocationWeatherFragment.newInstance()
            )
            .commitAllowingStateLoss()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (drawerLayout.isDrawerOpen(navView)) {
                    drawerLayout.close()
                } else {
                    drawerLayout.openDrawer(GravityCompat.START)
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
