package com.marcgdiez.jsonplaceholder.repository

import com.marcgdiez.jsonplaceholder.business.Comment
import com.marcgdiez.jsonplaceholder.business.Item
import com.marcgdiez.jsonplaceholder.datasource.NetworkDataSource

class ItemsRepositoryImpl(private val network: NetworkDataSource) : ItemsRepository{

    override suspend fun getItems(): List<Item>  = network.getItems()

    override suspend fun getCommentsById(id : String): List<Comment> = network.getComments(id)

}