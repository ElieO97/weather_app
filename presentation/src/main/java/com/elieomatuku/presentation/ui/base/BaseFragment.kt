package com.elieomatuku.presentation.ui.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elieomatuku.presentation.extensions.lifecycleAwareLazy
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

/**
 * Created by elieomatuku on 2021-06-13
 */

class BaseFragment : Fragment, KodeinAware {

    constructor()
    constructor(@LayoutRes resId: Int) : super(resId)

    val viewModelFactory: ViewModelProvider.Factory by instance()

    protected inline fun <reified VM : ViewModel> getViewModel(): VM =
        ViewModelProvider(this).get(VM::class.java)

    protected inline fun <reified VM : ViewModel> getSharedViewModel(): VM =
        ViewModelProvider(requireActivity()).get(VM::class.java)

    protected inline fun <reified VM : ViewModel> viewModel(): Lazy<VM> = lifecycleAwareLazy(this) {
        getViewModel<VM>()
    }

    protected inline fun <reified VM : ViewModel> sharedViewModel(): Lazy<VM> =
        lifecycleAwareLazy(this) {
            getSharedViewModel<VM>()
        }

    override val kodein: Kodein by closestKodein(requireContext())
}
