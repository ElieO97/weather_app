package com.elieomatuku.presentation.ui.favourites

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elieomatuku.domain.model.Location
import com.elieomatuku.presentation.R
import com.elieomatuku.presentation.ui.base.BaseActivity
import com.elieomatuku.presentation.ui.search.SearchResultActivity
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_favourites.*
import kotlinx.android.synthetic.main.activity_favourites.emptyTv
import kotlinx.android.synthetic.main.activity_favourites.progressBar
import kotlinx.android.synthetic.main.activity_search_result.*
import timber.log.Timber

/**
 * Created by elieomatuku on 2021-06-17
 */

class FavouritesActivity : BaseActivity(R.layout.activity_favourites) {

    private val viewModel: FavouritesViewModel by viewModel<FavouritesViewModel>()

    private val favouritesRv: RecyclerView by lazy {
        val view = favouritesRV
        view.layoutManager = LinearLayoutManager(this)
        view
    }

    private val locationSelectionPublisher: PublishSubject<Location> by lazy {
        PublishSubject.create<Location>()
    }
    private val locationSelectionObservable: Observable<Location>
        get() = locationSelectionPublisher.hide()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.favourites)

        viewModel.viewState.observe(this) {
            Timber.d("favouritesViewState = $it")
            progressBar.isVisible = it.isLoading

            it.favourites?.let { favourites ->
                emptyTv.isVisible = favourites.isEmpty()

                if (favourites.isNotEmpty()) {
                    favouritesRv.adapter = FavouritesAdapter(favourites, locationSelectionPublisher)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavouriteLocations()
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
                        query?.let {

                            val intent =
                                Intent(
                                    this@FavouritesActivity,
                                    SearchResultActivity::class.java
                                )
                            intent.putExtra("query", it)
                            startActivity(intent)
                        }
                        return false
                    }
                })
            }
        }

        return super.onCreateOptionsMenu(menu)
    }
}
