package com.demo.qagency.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.demo.qagency.data.paging.CommentSource
import com.demo.qagency.data.remote.CommentsApi
import com.demo.qagency.domain.models.Comment
import com.demo.qagency.domain.repository.CommentsRepository
import com.demo.qagency.util.Constants
import kotlinx.coroutines.flow.Flow

class CommentImpl(
    private val api: CommentsApi
): CommentsRepository {

    override val comments: Flow<PagingData<Comment>>
        get() = Pager(PagingConfig(pageSize = Constants.DEFAULT_PAGE_SIZE)) {
            CommentSource(api)
        }.flow
}