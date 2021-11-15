package com.demo.qagency.data.remote

import com.demo.qagency.data.remote.dto.CommentItemDto
import retrofit2.http.GET

interface CommentsApi {

    @GET("comments")
    suspend fun getComments(): List<CommentItemDto>
}