package com.agvicente.gptprojectdemo.services;

import com.agvicente.gptprojectdemo.adapters.ChatMessageAdapter;
import com.agvicente.gptprojectdemo.config.Configuration;
import com.agvicente.gptprojectdemo.entities.Conversation;
import com.agvicente.gptprojectdemo.entities.Message;
import com.agvicente.gptprojectdemo.repositories.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConversationService {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ConversationRepository conversationRepository;

    public Conversation saveNewConversation(){
        Conversation conversation = new Conversation();
        return conversationRepository.save(conversation);
    }

    public Conversation saveMessageOn(Conversation conversation, Message message){
        message = messageService.saveMessage(message, conversation);
        conversation.getMessages().add(message);
        return conversation;
    }

    public Conversation saveConfigMessage(Conversation conversation, Configuration config){
        Message message = messageService.saveMessage(messageService.createMessage(
                                        ChatMessageAdapter.ROLE_SYSTEM,
                                        config.getChatMessageConfig1()),
                                        conversation);
        conversation.getMessages().add(message);
        return  conversation;
    }
}