package com.elieomatuku.presentation.ui.favourites

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.elieomatuku.presentation.R
import com.elieomatuku.presentation.ui.base.BaseActivity
import timber.log.Timber

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

        val searchView = menu?.findItem(R.id.menu_action_search)?.actionView
        if (searchView is SearchView) {
            searchView.setQueryHint(getString(R.string.search_hint))

            val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
            searchView.apply {
                setSearchableInfo(searchManager.getSearchableInfo(componentName))

                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(newText: String?): Boolean {
                        return true
                    }

                    override fun onQueryTextSubmit(query: String?): Boolean {
                        Timber.d("onQueryTextSubmit  = $query")
                        return false
                    }
                })
            }
        }

        return super.onCreateOptionsMenu(menu)
    }
}
