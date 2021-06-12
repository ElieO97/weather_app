package com.elieomatuku.domain.interactor

/**
 * Created by elieomatuku on 2021-06-12
 */

inline fun <R> safeInteractorCall(call: () -> R): CompleteResult<R> {
    return try {
        Success(call())
    } catch (t: Throwable) {
        t.printStackTrace()
        Fail(t)
    }
}
