package com.agvicente.gptprojectdemo.services;

import com.agvicente.gptprojectdemo.entities.Conversation;
import com.agvicente.gptprojectdemo.entities.Message;
import com.agvicente.gptprojectdemo.repositories.ConversationRepository;
import com.agvicente.gptprojectdemo.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message saveMessage(Message message, Conversation conversation){
        message.setConversation(conversation);
        message.setDate(new Date());
        return  messageRepository.save(message);
    }

    public Collection<Message> saveMessages(Collection<Message> messages, Conversation conversation){
        Date date = new Date();
        for(Message message : messages){
            message.setConversation(conversation);
            message.setDate(date);
        }
        return  messageRepository.saveAll(messages);
    }

    public static Message createMessage(String role, String content){
        Message message = new Message();
        message.setRole(role);
        message.setContent(content);
        message.setDate(new Date());
        return  message;
    }
}
