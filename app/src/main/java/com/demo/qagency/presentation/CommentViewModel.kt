package com.demo.qagency.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.demo.qagency.domain.use_case.GetCommentsUseCase
import javax.inject.Inject

class CommentViewModel @Inject constructor(
    private val getActivities: GetCommentsUseCase
) : ViewModel() {

    val activities = getActivities().cachedIn(viewModelScope)

    private val _state = mutableStateOf(CommentState())
    val state: State<CommentState> = _state

}