package com.nick.kotlinsocket.dto

data class UserMessageDto(
    val type: String,
    val userHashId: String,
    val msg: String?,
)