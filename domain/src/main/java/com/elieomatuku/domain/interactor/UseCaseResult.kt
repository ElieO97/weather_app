package com.elieomatuku.domain.interactor

/**
 * Created by elieomatuku on 2021-06-12
 */

sealed class UseCaseResult<out T>

sealed class IncompleteResult<out T> : UseCaseResult<T>()
sealed class CompleteResult<out T> : UseCaseResult<T>()

data class Success<out T>(val data: T) : CompleteResult<T>()
data class Fail(val throwable: Throwable) : CompleteResult<Nothing>()
object Uninitialized : IncompleteResult<Nothing>()
class Loading<out T> : IncompleteResult<T>()
