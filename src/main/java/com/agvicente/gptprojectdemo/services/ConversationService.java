package com.agvicente.gptprojectdemo.services;

import com.agvicente.gptprojectdemo.adapters.ChatMessageAdapter;
import com.agvicente.gptprojectdemo.config.Configuration;
import com.agvicente.gptprojectdemo.entities.Conversation;
import com.agvicente.gptprojectdemo.entities.Message;
import com.agvicente.gptprojectdemo.model.enums.InteractionTypeEnum;
import com.agvicente.gptprojectdemo.repositories.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

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

    public Conversation getConversationById(Long id) throws Exception {
        if(id == null) throw new Exception();
        return conversationRepository.getReferenceById(id);
    }

    public Conversation saveMessageOn(Conversation conversation, Message message){
        message = messageService.saveMessage(message, conversation);
        conversation.getMessages().add(message);
        return conversation;
    }

    public Conversation saveMessagesOn(Conversation conversation, Collection<Message> messages){
        messages = messageService.saveMessages(messages, conversation);
        conversation.getMessages().addAll(messages);
        return conversation;
    }

    public Conversation saveConfigMessageOn(InteractionTypeEnum interactionType, Conversation conversation, Configuration config){
        Message message = messageService.saveMessage(messageService.createMessage(
                                        ChatMessageAdapter.ROLE_SYSTEM,
                                        config.getConfigMessages().get(interactionType)),
                                        conversation);
        conversation.getMessages().add(message);
        return  conversation;
    }
}