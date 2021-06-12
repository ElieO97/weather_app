package com.elieomatuku.domain.interactor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * Created by elieomatuku on 2021-06-12
 */

inline fun <R> safeUseCaseCall(call: () -> R): CompleteResult<R> {
    return try {
        Success(call())
    } catch (t: Throwable) {
        t.printStackTrace()
        Fail(t)
    }
}

suspend fun <I, T> runUseCase(
    useCase: UseCase<I, T>,
    input: I,
    coroutineContext: CoroutineContext = Dispatchers.IO
): T {
    return withContext(coroutineContext) { useCase.execute(input) }
}
