package com.demo.qagency.domain.use_case

import com.demo.qagency.domain.models.Comment
import com.demo.qagency.domain.repository.CommentsRepository
import com.demo.qagency.util.Resource

class GetCommentsUseCase(
    private val repository: CommentsRepository
) {

    suspend operator fun invoke(page: Int): Resource<List<Comment>> {
        return repository.getPostsPaged(
                page = page
        )
    }
}