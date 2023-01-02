package com.demo.qagency.domain.use_case

import com.demo.qagency.domain.models.Comment
import com.demo.qagency.domain.repository.CommentsRepository
import com.demo.qagency.util.Resource
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GetCommentsUseCaseTest {
    private lateinit var repository: CommentsRepository
    private lateinit var useCase: GetCommentsUseCase

    @Before
    fun setUp() {
        repository = Mockito.mock(CommentsRepository::class.java)
        useCase = GetCommentsUseCase(repository)
    }

    @Test
    fun `when use case is invoked, it returns a flow of comments from the repository`() = runBlocking {
        // given
        val expectedComments =
            listOf(
                Comment(id = 1, name = "test1", body = "body1", postId = 1, email = "email1"),
                Comment(id = 2, name = "test2", body = "body2", postId = 2, email = "email2")
            )
        Mockito.`when`(repository.getPostsPaged(page = 1)).thenReturn(
            flow {
                emit(Resource.Success(expectedComments))
            }
        )

        // when
        val result = useCase(page = 1)

        // then
        assertEquals(expectedComments, result.toList().first().data)
    }
}
