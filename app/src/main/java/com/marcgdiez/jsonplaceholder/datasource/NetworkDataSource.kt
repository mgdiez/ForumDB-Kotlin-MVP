package com.marcgdiez.jsonplaceholder.datasource

import com.marcgdiez.jsonplaceholder.business.Comment
import com.marcgdiez.jsonplaceholder.business.Item
import com.marcgdiez.jsonplaceholder.repository.ItemsApi
import com.marcgdiez.jsonplaceholder.repository.mapper.ItemsMapper

class NetworkDataSource(
    private val itemsApi: ItemsApi,
    private val mapper: ItemsMapper
) {

    suspend fun getItems(): List<Item> =
        try {
            itemsApi.getItems().await().map { itemsResponseDto -> mapper.map(itemsResponseDto) }
        } catch (e: Exception) {
            throw NetworkSourceException()
        }

    suspend fun getComments(id: String): List<Comment> =
        try {
            itemsApi.getItemComments(id).await().map { commentsResponseDto -> mapper.map(commentsResponseDto) }
        } catch (e: Exception) {
            throw NetworkSourceException()
        }
}