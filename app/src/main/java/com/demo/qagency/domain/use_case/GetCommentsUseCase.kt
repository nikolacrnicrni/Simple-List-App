package com.demo.qagency.domain.use_case

import com.demo.qagency.domain.models.Comment
import com.demo.qagency.domain.repository.CommentsRepository
import com.demo.qagency.util.Resource
import kotlinx.coroutines.flow.Flow

class GetCommentsUseCase(
    private val repository: CommentsRepository
) {

    suspend operator fun invoke(page: Int): Flow<Resource<List<Comment>>> {
        return repository.getPostsPaged(
            page = page
        )
    }
}
