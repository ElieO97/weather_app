package com.elieomatuku.presentation.ui.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.elieomatuku.presentation.extensions.lifecycleAwareLazy

/**
 * Created by elieomatuku on 2021-06-13
 */

abstract class BaseActivity : AppCompatActivity {

    constructor() : super()
    constructor(@LayoutRes resId: Int) : super(resId)

    protected inline fun <reified VM : ViewModel> getViewModel(): VM =
        getViewModel(viewModelFactory)

    protected inline fun <reified VM : ViewModel> viewModel(): Lazy<VM> {
        return lifecycleAwareLazy(this) { getViewModel<VM>() }
    }
}
