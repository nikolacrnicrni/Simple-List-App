package com.demo.qagency.presentation

import com.demo.qagency.domain.models.Comment

data class CommentState(
    val activities: List<Comment> = emptyList(),
    val isLoading: Boolean = false
)