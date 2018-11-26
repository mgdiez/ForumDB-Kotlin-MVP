package com.marcgdiez.jsonplaceholder.list.usecase

import com.marcgdiez.jsonplaceholder.business.Item
import com.marcgdiez.jsonplaceholder.core.Either
import com.marcgdiez.jsonplaceholder.core.Failure
import com.marcgdiez.jsonplaceholder.datasource.NetworkSourceException
import com.marcgdiez.jsonplaceholder.repository.ItemsRepository

class GetItemsListUseCase(private val itemsRepository: ItemsRepository) {

    @Throws(NetworkSourceException::class)
    suspend fun execute(): Either<Failure, List<Item>> = itemsRepository.getItems()
}