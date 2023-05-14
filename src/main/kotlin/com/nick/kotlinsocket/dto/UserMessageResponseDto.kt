package com.nick.kotlinsocket.dto

data class UserMessageResponseDto(
    val userHashIdList: MutableList<String>,
    val msg: String?
)