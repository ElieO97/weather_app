package com.elieomatuku.presentation.ui.favourites

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.elieomatuku.presentation.R
import com.elieomatuku.presentation.ui.base.BaseActivity

/**
 * Created by elieomatuku on 2021-06-17
 */

class FavouritesActivity : BaseActivity(R.layout.activity_favourites) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.favourites)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.clear()
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchView = menu?.findItem(R.id.menu_action_search)?.actionView as SearchView?
        searchView?.queryHint = "Test"

        return super.onCreateOptionsMenu(menu)
    }
}
