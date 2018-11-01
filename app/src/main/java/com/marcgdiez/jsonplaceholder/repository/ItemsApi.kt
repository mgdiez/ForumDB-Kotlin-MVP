package com.marcgdiez.jsonplaceholder.repository

import com.marcgdiez.jsonplaceholder.datasource.dto.CommentResponseDto
import com.marcgdiez.jsonplaceholder.datasource.dto.ItemsResponseDto
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface ItemsApi {

    @GET("posts/")
    fun getItems(): Deferred<List<ItemsResponseDto>>

    @GET("/posts/{id}/comments")
    fun getItemComments(@Path("id") id: String): Deferred<List<CommentResponseDto>>
}