package com.elieomatuku.domain.repository

/**
 * Created by elieomatuku on 2021-06-12
 */

data class RepositoryException(
    val code: Int,
    val errorBody: String?,
    val msg: String
) : RuntimeException(msg)
