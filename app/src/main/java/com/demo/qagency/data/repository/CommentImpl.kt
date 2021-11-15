package com.demo.qagency.data.repository

import com.demo.qagency.data.remote.CommentsApi
import kotlinx.coroutines.flow.Flow

class CommentImpl(
    private val api: CommentsApi
) {

    override val comments: Flow<PagingData>
}