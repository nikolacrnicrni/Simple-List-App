package com.demo.qagency.di

import android.app.Application
import androidx.room.Room
import com.demo.qagency.data.local.CommentDatabase
import com.demo.qagency.data.remote.CommentsApi
import com.demo.qagency.data.repository.CommentImpl
import com.demo.qagency.domain.repository.CommentsRepository
import com.demo.qagency.domain.use_case.GetCommentsUseCase
import com.demo.qagency.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCommentApi(): CommentsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CommentsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCommentRepository(api: CommentsApi, db: CommentDatabase): CommentsRepository {
        return CommentImpl(api = api, dao = db.dao)
    }

    @Provides
    @Singleton
    fun provideCommentsDatabase(app: Application): CommentDatabase {
        return Room.databaseBuilder(
            app, CommentDatabase::class.java, "qagency_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideGetCommentsUseCase(repository: CommentsRepository): GetCommentsUseCase {
        return GetCommentsUseCase(repository)
    }
}
