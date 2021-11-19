package com.demo.qagency.data.repository

import com.demo.qagency.R
import com.demo.qagency.data.local.CommentDao
import com.demo.qagency.data.remote.CommentsApi
import com.demo.qagency.domain.models.Comment
import com.demo.qagency.domain.repository.CommentsRepository
import com.demo.qagency.util.Constants
import com.demo.qagency.util.Resource
import com.demo.qagency.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CommentImpl(
    private val api: CommentsApi,
    private val dao: CommentDao
): CommentsRepository {

    override suspend fun getPostsPaged(page: Int, pageSize: Int): Flow<Resource<List<Comment>>> = flow {

        val oldComments = dao.getComments(pageSize = pageSize, pageIndex = page).map { it.toComment() }
        try {
            val remoteComments = api.getComments(
                page = page,
                limit = pageSize
            )

            dao.deleteComments(remoteComments.map { it.id })
            dao.insertComments(remoteComments.map { it.toComment() })

        } catch(e: IOException) {
            emit(Resource.Error(uiText = UiText.StringResource(R.string.error_couldnt_reach_server), data = oldComments))
        } catch(e: HttpException) {
            emit(Resource.Error(uiText = UiText.StringResource(R.string.oops_something_went_wrong), data = oldComments))

        }

        val newComments = dao.getComments(pageSize = pageSize, pageIndex = page).map { it.toComment() }
        emit(Resource.Success(data = newComments))

    }
}