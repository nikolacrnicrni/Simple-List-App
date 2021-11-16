package com.demo.qagency.di

import com.demo.qagency.data.remote.CommentsApi
import com.demo.qagency.data.repository.CommentImpl
import com.demo.qagency.domain.repository.CommentsRepository
import com.demo.qagency.domain.use_case.GetCommentsUseCase
import com.demo.qagency.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCommentApi(client: OkHttpClient): CommentsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CommentsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCommentRepository(api: CommentsApi): CommentsRepository {
        return CommentImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetCommentsUseCase(repository: CommentsRepository): GetCommentsUseCase {
        return GetCommentsUseCase(repository)
    }
}