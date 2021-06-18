package com.elieomatuku.presentation.ui.search

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elieomatuku.presentation.R
import com.elieomatuku.presentation.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_search_result.*
import timber.log.Timber

/**
 * Created by elieomatuku on 2021-06-17
 */

class SearchResultActivity : BaseActivity(R.layout.activity_search_result) {

    private val viewModel: SearchViewModel by viewModel<SearchViewModel>()

    private val resultsRv: RecyclerView by lazy {
        val view = resultRV
        view.layoutManager = LinearLayoutManager(this)
        view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val query = intent.getStringExtra("query")
        query?.let {
            viewModel.searchLocation(it)
        }

        viewModel.viewState.observe(this) {
            Timber.d("searchViewState = $it")

            if (!it.searchResult.isNullOrEmpty()) {
                emptyTv.isVisible = false
                resultsRv.adapter = SearchResultAdapter(it.searchResult)
            } else {
                emptyTv.isVisible = true
            }
        }
    }
}
