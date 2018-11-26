package com.marcgdiez.jsonplaceholder.repository

import com.marcgdiez.jsonplaceholder.business.Comment
import com.marcgdiez.jsonplaceholder.business.Item
import com.marcgdiez.jsonplaceholder.core.Either
import com.marcgdiez.jsonplaceholder.core.Failure
import com.marcgdiez.jsonplaceholder.datasource.NetworkDataSource

interface ItemsRepository {

    suspend fun getItems(): Either<Failure, List<Item>>
    suspend fun getCommentsById(id: String): Either<Failure, List<Comment>>

    class NetworkRepository(private val network: NetworkDataSource) : ItemsRepository {

        override suspend fun getItems(): Either<Failure, List<Item>> = network.getItems()

        override suspend fun getCommentsById(id: String): Either<Failure, List<Comment>> = network.getComments(id)
    }
}