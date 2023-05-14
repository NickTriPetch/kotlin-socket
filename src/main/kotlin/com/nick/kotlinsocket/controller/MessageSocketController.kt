package com.nick.kotlinsocket.controller

import com.nick.kotlinsocket.dto.UserMessageDto
import com.nick.kotlinsocket.dto.UserMessageResponseDto
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.annotation.SendToUser
import org.springframework.stereotype.Controller


@Controller
class MessageSocketController {
    private var userHashIdList: MutableList<String> = mutableListOf()

    @MessageMapping("/users/online")
    @SendTo("/users/online")
    fun broadcastUsers(
        @Payload userMessageDto: UserMessageDto,
    ): UserMessageResponseDto? {
        if (userMessageDto.type == "join") {
            userHashIdList.add(userMessageDto.userHashId)
        } else if (userMessageDto.type == "leave") {
            userHashIdList = userHashIdList.filter { it.contains(userMessageDto.userHashId) }.toMutableList()
        }

        println("userList > $userHashIdList")

        return UserMessageResponseDto(
            userHashIdList = userHashIdList,
            msg = userMessageDto.msg,
        )
    }
}