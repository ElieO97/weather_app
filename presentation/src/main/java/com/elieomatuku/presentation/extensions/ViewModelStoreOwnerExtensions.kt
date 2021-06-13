package com.elieomatuku.presentation.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import java.io.Serializable

/**
 * Created by elieomatuku on 2021-06-13
 */

private object UninitializedValue

class lifecycleAwareLazy<out T>(private val owner: LifecycleOwner, initializer: () -> T) :
    Lazy<T>,
    Serializable {
    private var initializer: (() -> T)? = initializer

    @Volatile
    private var _value: Any? = UninitializedValue
    private val lock = this

    init {
        owner.lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun onStart() {
                if (!isInitialized()) value
                owner.lifecycle.removeObserver(this)
            }
        })
    }

    override val value: T
        get() {
            val _v1 = _value
            if (_v1 !== UninitializedValue) {
                return _v1 as T
            }

            return synchronized(lock) {
                val _v2 = _value
                if (_v2 !== UninitializedValue) {
                    (_v2 as T)
                } else {
                    val typedValue = initializer!!()
                    _value = typedValue
                    initializer = null
                    typedValue
                }
            }
        }

    override fun isInitialized(): Boolean = _value !== UninitializedValue

    override fun toString(): String =
        if (isInitialized()) value.toString() else "Lazy value not initialized yet."
}
