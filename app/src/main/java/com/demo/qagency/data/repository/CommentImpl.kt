package com.demo.qagency.data.repository

import com.demo.qagency.R
import com.demo.qagency.data.remote.CommentsApi
import com.demo.qagency.domain.models.Comment
import com.demo.qagency.domain.repository.CommentsRepository
import com.demo.qagency.util.Resource
import com.demo.qagency.util.UiText
import retrofit2.HttpException
import java.io.IOException

class CommentImpl(
    private val api: CommentsApi
): CommentsRepository {
    override suspend fun getPostsPaged(page: Int, pageSize: Int): Resource<List<Comment>> {
        return try {
            val posts = api.getComments(
                    page = page,
                    limit = pageSize
            )
            Resource.Success(data = posts)
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