package com.demo.qagency.domain.repository

import com.demo.qagency.domain.models.Comment
import com.demo.qagency.util.Constants
import com.demo.qagency.util.Resource
import kotlinx.coroutines.flow.Flow

interface CommentsRepository {

    suspend fun getPostsPaged(
        page: Int = 0,
        pageSize: Int = Constants.DEFAULT_PAGE_SIZE
    ): Flow<Resource<List<Comment>>>
}
