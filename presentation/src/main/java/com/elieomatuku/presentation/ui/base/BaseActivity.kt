package com.elieomatuku.presentation.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elieomatuku.presentation.extensions.getViewModel
import com.elieomatuku.presentation.extensions.lifecycleAwareLazy
import com.google.android.gms.location.FusedLocationProviderClient
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

/**
 * Created by elieomatuku on 2021-06-13
 */

abstract class BaseActivity : AppCompatActivity, KodeinAware {

    constructor() : super()
    constructor(@LayoutRes resId: Int) : super(resId)

    override val kodein: Kodein by closestKodein()
    val viewModelFactory: ViewModelProvider.Factory by instance()
    val fusedLocationClient: FusedLocationProviderClient by instance()

    protected val rxSubs: io.reactivex.disposables.CompositeDisposable by lazy {
        io.reactivex.disposables.CompositeDisposable()
    }

    protected inline fun <reified VM : ViewModel> getViewModel(): VM =
        getViewModel(viewModelFactory)

    protected inline fun <reified VM : ViewModel> viewModel(): Lazy<VM> {
        return lifecycleAwareLazy(this) { getViewModel<VM>() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.elevation = 0f
    }

    override fun onDestroy() {
        rxSubs.clear()
        super.onDestroy()
    }
}
