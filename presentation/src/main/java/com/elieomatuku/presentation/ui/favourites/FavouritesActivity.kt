package com.elieomatuku.presentation.ui.favourites

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elieomatuku.domain.model.Location
import com.elieomatuku.presentation.R
import com.elieomatuku.presentation.ui.base.BaseActivity
import com.elieomatuku.presentation.ui.search.SearchResultActivity
import com.elieomatuku.presentation.ui.weather.WeatherActivity
import com.elieomatuku.presentation.utils.Constants
import com.elieomatuku.presentation.utils.SwipeToDeleteSimpleCallback
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
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
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        val drawable: Drawable? =
            ResourcesCompat.getDrawable(resources, R.drawable.item_decoration, theme)
        if (drawable != null) {
            itemDecoration.setDrawable(drawable)
        }
        view.addItemDecoration(itemDecoration)
        view.layoutManager = LinearLayoutManager(this)
        view
    }

    private val locationSelectionPublisher: PublishSubject<Location> by lazy {
        PublishSubject.create<Location>()
    }
    private val locationSelectionObservable: Observable<Location>
        get() = locationSelectionPublisher.hide()

    private val locationDeletionPublisher: PublishSubject<Location> by lazy {
        PublishSubject.create<Location>()
    }
    private val locationDeletionObservable: Observable<Location>
        get() = locationSelectionPublisher.hide()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.favourites)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        viewModel.viewState.observe(this) {
            Timber.d("favouritesViewState = $it")
            progressBar.isVisible = it.isLoading

            it.favourites?.let { favourites ->
                emptyTv.isVisible = favourites.isEmpty()

                if (favourites.isNotEmpty()) {
                    val adapter = FavouritesAdapter(
                        favourites.toMutableList(),
                        locationSelectionPublisher,
                        locationDeletionPublisher
                    )

                    val itemTouchHelper = ItemTouchHelper(SwipeToDeleteSimpleCallback(adapter))
                    itemTouchHelper.attachToRecyclerView(favouritesRv)
                    favouritesRv.adapter = adapter
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavouriteLocations()

        rxSubs.add(
            (
                locationSelectionObservable
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { location: Location ->
                            val intent = Intent(this, WeatherActivity::class.java)
                            intent.putExtra(Constants.LONG, location.longitude)
                            intent.putExtra(Constants.LAT, location.latitude)
                            intent.putExtra(Constants.LOCATION_NAME, location.name)

                            startActivity(intent)
                        },
                        { t: Throwable ->
                            Timber.e("addLocationObservable failed:$t")
                        }
                    )
                )
        )

        rxSubs.add(
            (
                locationDeletionObservable
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { location: Location ->
                            Timber.d("delete favourite location = $location")
                            viewModel.deleteFavouriteLocation(location)
                        },
                        { t: Throwable ->
                            Timber.e("addLocationObservable failed:$t")
                        }
                    )
                )
        )
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
                            intent.putExtra(Constants.QUERY, it)
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
