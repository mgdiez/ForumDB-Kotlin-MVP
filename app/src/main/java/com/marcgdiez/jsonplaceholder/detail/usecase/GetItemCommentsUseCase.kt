package com.marcgdiez.jsonplaceholder.detail.usecase

import com.marcgdiez.jsonplaceholder.business.Comment
import com.marcgdiez.jsonplaceholder.core.Either
import com.marcgdiez.jsonplaceholder.core.Failure
import com.marcgdiez.jsonplaceholder.datasource.NetworkSourceException
import com.marcgdiez.jsonplaceholder.repository.ItemsRepository

class GetItemCommentsUseCase(private val itemsRepository: ItemsRepository) {

    @Throws(NetworkSourceException::class)
    suspend fun execute(id: String): Either<Failure, List<Comment>> = itemsRepository.getCommentsById(id)
}