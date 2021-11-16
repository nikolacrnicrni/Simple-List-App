package com.demo.qagency.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.demo.qagency.data.remote.CommentsApi
import com.demo.qagency.domain.models.Comment
import com.demo.qagency.util.Constants
import retrofit2.HttpException
import java.io.IOException

class CommentSource(
    private val api: CommentsApi
): PagingSource<Int, Comment>() {

    private var currentPage = 0

    override fun getRefreshKey(state: PagingState<Int, Comment>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comment> {
        return try {
            val nextPage = params.key ?: currentPage
            val comments = api.getComments(
                page = nextPage,
                limit = Constants.DEFAULT_PAGE_SIZE
            )
            LoadResult.Page(
                data = comments.map { it.toComment() },
                prevKey = if (nextPage == 0) null else nextPage - 1,
                nextKey = if (comments.isEmpty()) null else currentPage + 1
            ).also { currentPage++ }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }


}