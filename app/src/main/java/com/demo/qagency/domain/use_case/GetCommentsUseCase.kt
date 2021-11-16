package com.demo.qagency.domain.use_case

import androidx.paging.PagingData
import com.demo.qagency.domain.models.Comment
import com.demo.qagency.domain.repository.CommentsRepository
import kotlinx.coroutines.flow.Flow

class GetCommentsUseCase(
    private val repository: CommentsRepository
) {

    operator fun invoke(): Flow<PagingData<Comment>>{
        return repository.comments
    }
}