package com.nick.kotlinsocket.controller

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.annotation.SendToUser
import org.springframework.stereotype.Controller
import java.security.Principal


@Controller
class MessageSocketController {
    @MessageMapping("/news")
    @SendTo("/topic/news")
    fun broadcastNews(@Payload message: String?): String? {
        println("========== broadcastNews ==========")
        println("Message: $message")

        return message
    }

    @MessageMapping("/greetings")
    @SendToUser("/queue/greetings")
    fun reply(
        @Payload message: String,
        user: Principal?
    ): String? {
        return "Hello $message"
    }
}