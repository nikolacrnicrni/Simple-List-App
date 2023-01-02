package com.demo.qagency.data.remote.dto

import com.demo.qagency.data.local.entity.CommentsEntity
import com.google.gson.annotations.SerializedName

data class CommentItemDto(
    @SerializedName("body")
    val body: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("postId")
    val postId: Int
) {
    fun toComment(): CommentsEntity {
        return CommentsEntity(
            body = body,
            email = email,
            id = id,
            name = name,
            postId = postId
        )
    }
}
