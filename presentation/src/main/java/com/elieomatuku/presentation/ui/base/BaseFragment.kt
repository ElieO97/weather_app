package com.elieomatuku.presentation.ui.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elieomatuku.presentation.extensions.getSharedViewModel
import com.elieomatuku.presentation.extensions.getViewModel
import com.elieomatuku.presentation.extensions.lifecycleAwareLazy
import com.google.android.gms.location.FusedLocationProviderClient
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

/**
 * Created by elieomatuku on 2021-06-13
 */

abstract class BaseFragment : Fragment, KodeinAware {

    constructor()
    constructor(@LayoutRes resId: Int) : super(resId)

    override val kodein: Kodein by kodein()
    val viewModelFactory: ViewModelProvider.Factory by instance()
    val fusedLocationClient: FusedLocationProviderClient by instance()

    protected inline fun <reified VM : ViewModel> getViewModel(): VM =
        getViewModel(viewModelFactory)

    protected inline fun <reified VM : ViewModel> getSharedViewModel(): VM =
        getSharedViewModel(viewModelFactory)

    protected inline fun <reified VM : ViewModel> viewModel(): Lazy<VM> = lifecycleAwareLazy(this) {
        getViewModel<VM>()
    }

    protected inline fun <reified VM : ViewModel> sharedViewModel(): Lazy<VM> =
        lifecycleAwareLazy(this) {
            getSharedViewModel<VM>()
        }
}
