package com.elieomatuku.domain.interactor

/**
 * Created by elieomatuku on 2021-06-12
 */

interface UseCase<In, Out> {
    suspend fun execute(params: In): Out
}

typealias NoInputUseCase<Out> = UseCase<Unit, Out>

typealias NoOutputUseCase<In> = UseCase<In, Unit>
