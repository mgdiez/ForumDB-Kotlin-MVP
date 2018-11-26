package com.marcgdiez.jsonplaceholder.datasource

import com.marcgdiez.jsonplaceholder.business.Comment
import com.marcgdiez.jsonplaceholder.business.Item
import com.marcgdiez.jsonplaceholder.core.Either
import com.marcgdiez.jsonplaceholder.core.Failure
import com.marcgdiez.jsonplaceholder.repository.ItemsApi
import com.marcgdiez.jsonplaceholder.repository.mapper.ItemsMapper

class NetworkDataSource(
    private val itemsApi: ItemsApi,
    private val mapper: ItemsMapper
) {

    suspend fun getItems(): Either<Failure, List<Item>> =
        try {
            Either.Right(itemsApi.getItems().await().map { mapper.map(it) })
        } catch (e: Exception) {
            Either.Left(Failure.NetworkError())
        }

    suspend fun getComments(id: String): Either<Failure, List<Comment>> =
        try {
            Either.Right(itemsApi.getItemComments(id).await().map { mapper.map(it) })
        } catch (e: Exception) {
            Either.Left(Failure.NetworkError())
        }
}