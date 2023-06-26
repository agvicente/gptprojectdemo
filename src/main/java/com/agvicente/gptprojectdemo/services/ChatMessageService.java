package com.agvicente.gptprojectdemo.services;

import com.agvicente.gptprojectdemo.entities.Message;
import com.theokanning.openai.completion.chat.ChatMessage;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageService {

    public ChatMessage messageToChatMessage(Message message){
        ChatMessage msg = new ChatMessage();
        msg.setRole(message.getRole());
        msg.setContent(message.getContent());

        return msg;
    }
}
