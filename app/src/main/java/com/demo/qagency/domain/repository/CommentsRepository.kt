package com.demo.qagency.domain.repository

import androidx.paging.PagingData
import com.demo.qagency.domain.models.Comment
import com.demo.qagency.util.Resource
import kotlinx.coroutines.flow.Flow

interface CommentsRepository {

    val comments: Flow<PagingData<Comment>>
}