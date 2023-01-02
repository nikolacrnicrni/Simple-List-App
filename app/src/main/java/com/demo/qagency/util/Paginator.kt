package com.demo.qagency.util

interface Paginator<T> {
    suspend fun loadNextItems()
}
