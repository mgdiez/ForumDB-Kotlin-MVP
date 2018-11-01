package com.marcgdiez.jsonplaceholder.datasource.dto

data class CommentResponseDto(val postId : String, val id : String,
                              val name : String, val email : String,
                              val body : String)