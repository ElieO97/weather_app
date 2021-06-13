package com.elieomatuku.presentation.injection

import android.content.Context
import org.kodein.di.Kodein

/**
 * Created by elieomatuku on 2021-06-13
 */

object PresentationKodeinModule {

    const val moduleName = "presentation"

    fun getModule(context: Context): Kodein.Module {

        return Kodein.Module(name = moduleName) {
        }
    }
}
