package com.elieomatuku.presentation.ui.search

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elieomatuku.domain.model.Location
import com.elieomatuku.presentation.R
import com.elieomatuku.presentation.ui.base.BaseActivity
import com.elieomatuku.presentation.utils.Constants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_search_result.*
import timber.log.Timber

/**
 * Created by elieomatuku on 2021-06-17
 */

class SearchResultActivity : BaseActivity(R.layout.activity_search_result) {

    private val viewModel: SearchViewModel by viewModel<SearchViewModel>()

    private val resultsRv: RecyclerView by lazy {
        val view = resultRV
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

    private val addLocationPublisher: PublishSubject<Location> by lazy {
        PublishSubject.create<Location>()
    }
    private val addLocationObservable: Observable<Location>
        get() = addLocationPublisher.hide()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.search)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        val query = intent.getStringExtra(Constants.QUERY)
        query?.let {
            viewModel.searchLocation(it)
        }

        viewModel.viewState.observe(this) {
            Timber.d("searchViewState = $it")

            progressBar.isVisible = it.isLoading

            it.searchResult?.let { result ->
                emptyTv.isVisible = result.isEmpty()

                if (result.isNotEmpty()) {
                    resultsRv.adapter = SearchResultAdapter(it.searchResult, addLocationPublisher)
                }
            }

            if (it.newFavouriteLocation) {
                onBackPressed()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        rxSubs.add(
            (
                addLocationObservable
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { location: Location ->
                            viewModel.saveFavouriteLocation(location)
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
}
