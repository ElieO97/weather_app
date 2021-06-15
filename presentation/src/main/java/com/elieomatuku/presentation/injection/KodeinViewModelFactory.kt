package com.elieomatuku.presentation.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.generic.instanceOrNull

/**
 * Created by elieomatuku on 2021-06-15
 */

class KodeinViewModelFactory(private val injector: Kodein) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return injector.direct.instanceOrNull<ViewModel>(tag = modelClass.simpleName) as T?
            ?: modelClass.newInstance()
    }
}
