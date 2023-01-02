package com.demo.qagency.data.remote

import com.demo.qagency.data.remote.dto.CommentItemDto
import com.demo.qagency.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentsApi {

    @GET("comments")
    suspend fun getComments(
        @Query("_page") page: Int = 0,
        @Query("_limit") limit: Int = Constants.DEFAULT_PAGE_SIZE
    ): List<CommentItemDto>
}
