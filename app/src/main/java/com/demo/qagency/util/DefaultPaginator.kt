package com.demo.qagency.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DefaultPaginator<T>(
    private val onLoadUpdated: (Boolean) -> Unit,
    private val onRequest: suspend (nextPage: Int) -> Flow<Resource<List<T>>>,
    private val onError: suspend (UiText) -> Unit,
    private val onSuccess: (items: List<T>) -> Unit,
    private val onScope: CoroutineScope
) : Paginator<T> {

    private var page = 0

    override suspend fun loadNextItems() {
        onLoadUpdated(true)
        onScope.launch {
            onRequest(page).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        val items = result.data ?: emptyList()
                        page++
                        onSuccess(items)
                        onLoadUpdated(false)
                    }
                    is Resource.Error -> {
                        onError(result.uiText ?: UiText.unknownError())
                        onLoadUpdated(false)
                    }
                }
            }.launchIn(this)
        }
    }
}
