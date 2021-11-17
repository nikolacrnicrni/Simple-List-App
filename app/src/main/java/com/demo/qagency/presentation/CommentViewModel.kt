package com.demo.qagency.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.qagency.domain.models.Comment
import com.demo.qagency.domain.use_case.GetCommentsUseCase
import com.demo.qagency.presentation.util.UiEvent
import com.demo.qagency.util.DefaultPaginator
import com.demo.qagency.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
        private val getActivities: GetCommentsUseCase,
) : ViewModel() {

    init {
        loadNextPosts()
    }

    fun loadNextPosts() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    private val _pagingState = mutableStateOf<PagingState<Comment>>(PagingState())
    val pagingState: State<PagingState<Comment>> = _pagingState

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val paginator = DefaultPaginator(
            onLoadUpdated = { isLoading ->
                _pagingState.value = pagingState.value.copy(
                        isLoading = isLoading
                )
            },
            onRequest = { page ->
                getActivities(
                        page = page
                )
            },
            onSuccess = { posts ->
                _pagingState.value = pagingState.value.copy(
                        items = pagingState.value.items + posts,
                        endReached = posts.isEmpty(),
                        isLoading = false
                )
            },
            onError = { uiText ->
                _eventFlow.emit(UiEvent.ShowSnackbar(uiText))
            }
    )

}