package com.demo.qagency.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.qagency.domain.models.Comment
import com.demo.qagency.domain.states.StandardCommentState
import com.demo.qagency.domain.use_case.GetCommentsUseCase
import com.demo.qagency.presentation.util.UiEvent
import com.demo.qagency.util.DefaultPaginator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val getComments: GetCommentsUseCase
) : ViewModel() {

    private val _pagingState = mutableStateOf<PagingState<Comment>>(PagingState())
    val pagingState: State<PagingState<Comment>> = _pagingState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _commentState = mutableStateOf(StandardCommentState())
    val commentState: State<StandardCommentState> = _commentState

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    private val paginator = DefaultPaginator(
        onLoadUpdated = { isLoading ->
            _pagingState.value = pagingState.value.copy(
                isLoading = isLoading
            )
        },
        onRequest = { page ->
            getComments(page = page)
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
        },
        onScope = viewModelScope
    )

    fun onSelectComment(comment: Comment) {
        viewModelScope.launch {
            _commentState.value = commentState.value.copy(
                comment = comment
            )
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            delay(700)
            _isRefreshing.emit(false)
        }
    }

    fun onEvent(event: CommentEvent) {
        when (event) {
            CommentEvent.ShowLogoutDialog -> {
                _pagingState.value = pagingState.value.copy(
                    isDialogVisible = true
                )
            }
            is CommentEvent.DismissLogoutDialog -> {
                _pagingState.value = pagingState.value.copy(
                    isDialogVisible = false
                )
            }
        }
    }

    init {
        loadNextPosts()
    }

    fun loadNextPosts() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }
}
