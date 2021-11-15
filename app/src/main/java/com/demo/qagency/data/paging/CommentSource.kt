package com.demo.qagency.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.demo.qagency.data.remote.CommentsApi
import com.demo.qagency.domain.models.Comment

class CommentSource(
    private val api: CommentsApi
): PagingSource<Int, Comment>() {
    override fun getRefreshKey(state: PagingState<Int, Comment>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comment> {
        TODO("Not yet implemented")
    }


}