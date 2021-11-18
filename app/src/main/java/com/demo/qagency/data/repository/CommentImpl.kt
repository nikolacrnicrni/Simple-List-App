package com.demo.qagency.data.repository

import com.demo.qagency.R
import com.demo.qagency.data.local.CommentDao
import com.demo.qagency.data.remote.CommentsApi
import com.demo.qagency.domain.models.Comment
import com.demo.qagency.domain.repository.CommentsRepository
import com.demo.qagency.util.Resource
import com.demo.qagency.util.UiText
import retrofit2.HttpException
import java.io.IOException

class CommentImpl(
    private val api: CommentsApi,
    private val dao: CommentDao
): CommentsRepository {

    override suspend fun getPostsPaged(page: Int, pageSize: Int): Resource<List<Comment>> {

        // Trebao sam dodati Resource state za loading da prikazuje dok se očitavaju ove stare komentare
        //val oldComments = dao.getComments().map { it.toComment() }

        return try {
            val remoteComments = api.getComments(
                page = page,
                limit = pageSize
            )

            dao.deleteComments(remoteComments.map { it.id })
            dao.insertComments(remoteComments.map { it.toComment() })

            val newComments = dao.getComments().map { it.toComment() }
            Resource.Success(data = newComments)
        } catch(e: IOException) {
            Resource.Error(
                    uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch(e: HttpException) {
            Resource.Error(
                    uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }

    }


}