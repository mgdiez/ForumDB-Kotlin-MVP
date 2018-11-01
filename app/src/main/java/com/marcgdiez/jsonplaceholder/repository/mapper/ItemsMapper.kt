package com.marcgdiez.jsonplaceholder.repository.mapper

import com.marcgdiez.jsonplaceholder.business.Comment
import com.marcgdiez.jsonplaceholder.business.Item
import com.marcgdiez.jsonplaceholder.datasource.dto.CommentResponseDto
import com.marcgdiez.jsonplaceholder.datasource.dto.ItemsResponseDto

class ItemsMapper {
    fun map(itemsResponseDto: ItemsResponseDto): Item =
        Item(itemsResponseDto.id, itemsResponseDto.title, itemsResponseDto.body)

    fun map(commentResponseDto: CommentResponseDto): Comment =
        Comment(commentResponseDto.id, commentResponseDto.name, commentResponseDto.email, commentResponseDto.body)
}