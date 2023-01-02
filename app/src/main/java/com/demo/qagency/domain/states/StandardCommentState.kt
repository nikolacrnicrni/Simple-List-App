package com.demo.qagency.domain.states

import com.demo.qagency.domain.models.Comment

data class StandardCommentState(
    val comment: Comment? = null,
    val error: Error? = null
)
