package com.demo.qagency.presentation

data class PagingState<T>(
    val items: List<T> = emptyList(),
    val isLoading: Boolean = false,
    val endReached: Boolean = false,
    val isDialogVisible: Boolean = false
)
