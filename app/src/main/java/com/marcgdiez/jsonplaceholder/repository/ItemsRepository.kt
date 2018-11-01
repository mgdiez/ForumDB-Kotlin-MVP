package com.marcgdiez.jsonplaceholder.repository

import com.marcgdiez.jsonplaceholder.business.Comment
import com.marcgdiez.jsonplaceholder.business.Item

interface ItemsRepository {

    suspend fun getItems(): List<Item>

    suspend fun getCommentsById(id : String) : List<Comment>
}