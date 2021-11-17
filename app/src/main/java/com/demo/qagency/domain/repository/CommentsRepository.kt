package com.demo.qagency.domain.repository

import com.demo.qagency.domain.models.Comment
import com.demo.qagency.util.Constants
import com.demo.qagency.util.Resource

interface CommentsRepository {

    suspend fun getPostsPaged(
            page: Int = 0,
            pageSize: Int = Constants.DEFAULT_PAGE_SIZE
    ): Resource<List<Comment>>

}