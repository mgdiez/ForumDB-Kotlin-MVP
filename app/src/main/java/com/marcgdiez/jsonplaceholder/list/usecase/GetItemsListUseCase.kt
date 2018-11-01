package com.marcgdiez.jsonplaceholder.list.usecase

import com.marcgdiez.jsonplaceholder.business.Item
import com.marcgdiez.jsonplaceholder.repository.ItemsRepository

class GetItemsListUseCase(private val itemsRepository: ItemsRepository) {

    suspend fun execute(): List<Item> = itemsRepository.getItems()
}