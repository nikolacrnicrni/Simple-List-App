package com.demo.qagency.domain.repository

import com.demo.qagency.domain.models.Comment
import com.demo.qagency.util.Resource

interface CommentsRepository {

    suspend fun getComments(): Resource<List<Comment>>
}