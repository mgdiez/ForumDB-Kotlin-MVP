package com.marcgdiez.jsonplaceholder.detail.usecase

import com.marcgdiez.jsonplaceholder.business.Comment
import com.marcgdiez.jsonplaceholder.repository.ItemsRepository

class GetItemCommentsUseCase(private val itemsRepository: ItemsRepository) {

    suspend fun execute(id : String): List<Comment> = itemsRepository.getCommentsById(id)
}