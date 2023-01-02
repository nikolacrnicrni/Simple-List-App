package com.demo.qagency.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.qagency.data.local.entity.CommentsEntity

@Dao
interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(comments: List<CommentsEntity>)

    @Query("DELETE FROM CommentsEntity WHERE id IN(:id)")
    suspend fun deleteComments(id: List<Int>)

    @Query("SELECT * FROM CommentsEntity LIMIT :pageSize OFFSET :pageIndex * :pageSize")
    suspend fun getComments(pageSize: Int, pageIndex: Int): List<CommentsEntity>
}
