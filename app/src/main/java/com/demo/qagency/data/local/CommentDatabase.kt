package com.demo.qagency.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.qagency.data.local.entity.CommentsEntity

@Database(
    entities = [CommentsEntity::class],
    version = 1
)
abstract class CommentDatabase : RoomDatabase() {

    abstract val dao: CommentDao
}
